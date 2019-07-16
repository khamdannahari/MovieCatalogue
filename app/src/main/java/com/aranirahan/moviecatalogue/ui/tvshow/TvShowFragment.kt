package com.aranirahan.moviecatalogue.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity.Companion.ID_TV_SHOW
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import com.aranirahan.moviecatalogue.utils.goGone
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.startActivity


class TvShowFragment : Fragment() {

    private var data = listOf<TvShow>()
    private val vmMain by lazy { activity?.let { obtainViewModel(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapterTvShow = TvShowAdapter { idMovie ->
            context?.startActivity<DetailTvShowActivity>(ID_TV_SHOW to idMovie)
        }

        vmMain?.tvShows?.observe(viewLifecycleOwner, Observer {
            progress_circular.goGone()
            data = it
            adapterTvShow.submitList(data)
        })

        rv_tv_show.apply {
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
