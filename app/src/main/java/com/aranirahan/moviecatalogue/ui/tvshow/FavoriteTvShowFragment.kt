package com.aranirahan.moviecatalogue.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity.Companion.ID_MOVIE
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity.Companion.ID_TV_SHOW
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import com.aranirahan.moviecatalogue.utils.goGone
import com.aranirahan.moviecatalogue.utils.goVisible
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.aranirahan.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.jetbrains.anko.startActivity


class FavoriteTvShowFragment : Fragment() {

    private var data = listOf<TvShow>()
    private val vmMain by lazy { activity?.let { obtainViewModel(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapterTvShow = TvShowAdapter { idTvShow ->
            context?.startActivity<DetailTvShowActivity>(ID_TV_SHOW to idTvShow)
        }

        vmMain?.favoriteTvShows?.observe(viewLifecycleOwner, Observer { response ->

            if (response != null) {
                when (response.status) {
                    Status.LOADING -> progress_circular.goVisible()
                    Status.SUCCESS -> {
                        progress_circular.goGone()
                        data = response.data ?: emptyList()
                        adapterTvShow.submitList(data)

                        if(data.isNullOrEmpty()){
                            tv_no_data.goVisible()
                        }
                    }
                    Status.ERROR -> {
                        progress_circular.goGone()
                        Toast.makeText(context, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })


        rv_favorite_tv_show.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTvShow
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

}
