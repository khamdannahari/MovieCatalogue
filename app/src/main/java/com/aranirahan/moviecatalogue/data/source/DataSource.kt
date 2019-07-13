package com.aranirahan.moviecatalogue.data.source

import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow


interface DataSource {

    fun getMovies(): List<Movie>

    fun getMovie(idMovie: Int): Movie

    fun getTvShows(): List<TvShow>

    fun getTvShow(idTvShow: Int): TvShow
}