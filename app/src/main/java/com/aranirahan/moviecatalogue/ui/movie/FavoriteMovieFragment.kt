package com.aranirahan.moviecatalogue.ui.movie


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
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity.Companion.ID_MOVIE
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import com.aranirahan.moviecatalogue.utils.goGone
import com.aranirahan.moviecatalogue.utils.goVisible
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.aranirahan.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.startActivity


class FavoriteMovieFragment : Fragment() {

    private var data = listOf<Movie>()
    private val vmMain by lazy { activity?.let { obtainViewModel(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapterMovie = MovieAdapter { idMovie ->
            context?.startActivity<DetailMovieActivity>(ID_MOVIE to idMovie)
        }

        vmMain?.favoriteMovies?.observe(viewLifecycleOwner, Observer { response ->

            if (response != null) {
                when (response.status) {
                    LOADING -> progress_circular.goVisible()
                    SUCCESS -> {
                        progress_circular.goGone()
                        data = response.data ?: emptyList()
                        adapterMovie.submitList(data)

                        if(data.isNullOrEmpty()){
                            tv_no_data.goVisible()
                        }
                    }
                    ERROR -> {
                        progress_circular.goGone()
                        Toast.makeText(context, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        rv_favorite_movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMovie
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

}
