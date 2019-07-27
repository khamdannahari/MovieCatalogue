package com.aranirahan.moviecatalogue.ui.movie


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
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity.Companion.ID_MOVIE
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import com.aranirahan.moviecatalogue.utils.goGone
import com.aranirahan.moviecatalogue.utils.goVisible
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.aranirahan.moviecatalogue.vo.Status
import com.aranirahan.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity


class MovieFragment : Fragment() {

    private var data = listOf<Movie>()
    private val vmMain by lazy { activity?.let { obtainViewModel(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapterMovie = MovieAdapter { idMovie ->
            context?.startActivity<DetailMovieActivity>(ID_MOVIE to idMovie)
        }

        vmMain?.movies?.observe(viewLifecycleOwner, Observer {
            data = it
            adapterMovie.submitList(data)

            getFavoriteMovies()
        })

        rv_movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMovie
        }
    }

    private fun getFavoriteMovies() {
        vmMain?.favoriteMovies?.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                SUCCESS -> {
                    progress_circular.goGone()
                    if (it.data.isNullOrEmpty()) {
                        vmMain?.insertFavoriteMovies(data)
                    }
                }
                ERROR -> {
                    progress_circular.goGone()
                }
                LOADING -> {
                }
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

}
