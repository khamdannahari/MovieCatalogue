package com.aranirahan.moviecatalogue.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.Movie
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity.Companion.ID_MOVIE
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity


class MovieFragment : Fragment() {

    private var data = listOf<Movie>()
    private val vmMain by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        data = vmMain.movies

        rv_movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(data) { idMovie ->
                context?.startActivity<DetailMovieActivity>(ID_MOVIE to idMovie)
            }
        }
    }
}
