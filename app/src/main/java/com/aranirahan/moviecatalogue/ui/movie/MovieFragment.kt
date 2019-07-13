package com.aranirahan.moviecatalogue.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity.Companion.ID_MOVIE
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.google.gson.Gson


class MovieFragment : Fragment() {

    private var data = listOf<Movie>()
    private val vmMain by lazy { activity?.let { obtainViewModel(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        data = vmMain?.movies ?: listOf()
        Log.d("dataMovieAll", Gson().toJson(data))
        Log.d("dataMovie1", Gson().toJson(data[0]))
        Log.d("dataMovie2", Gson().toJson(data[1]))
        Log.d("dataMovie3", Gson().toJson(data[2]))
        Log.d("dataMovie4", Gson().toJson(data[3]))
        Log.d("dataMovie5", Gson().toJson(data[4]))
        Log.d("dataMovie6", Gson().toJson(data[5]))
        Log.d("dataMovie7", Gson().toJson(data[6]))
        Log.d("dataMovie8", Gson().toJson(data[7]))
        Log.d("dataMovie9", Gson().toJson(data[8]))
        Log.d("dataMovie10", Gson().toJson(data[9]))
        Log.d("dataMovie11", Gson().toJson(data[10]))
        Log.d("dataMovie12", Gson().toJson(data[11]))
        Log.d("dataMovie13", Gson().toJson(data[12]))
        Log.d("dataMovie14", Gson().toJson(data[13]))
        Log.d("dataMovie15", Gson().toJson(data[14]))
        Log.d("dataMovie16", Gson().toJson(data[15]))

        rv_movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(data) { idMovie ->
                context?.startActivity<DetailMovieActivity>(ID_MOVIE to idMovie)
            }
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

}
