package com.aranirahan.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow


interface DataSource {

    fun getMovies(): LiveData<List<Movie>>

    fun getMovie(idMovie: Int): LiveData<Movie>

    fun getTvShows(): LiveData<List<TvShow>>

    fun getTvShow(idTvShow: Int): LiveData<TvShow>
}