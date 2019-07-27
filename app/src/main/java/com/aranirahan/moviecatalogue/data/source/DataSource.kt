package com.aranirahan.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.vo.Resource


interface DataSource {

    fun getMovies(): LiveData<List<Movie>>

    fun getMovie(idMovie: Int): LiveData<Movie>

    fun getTvShows(): LiveData<List<TvShow>>

    fun getTvShow(idTvShow: Int): LiveData<TvShow>

    fun getFavoriteMovies(): LiveData<Resource<List<Movie>>>?

    fun getFavoriteMovie(idMovie: Int): LiveData<Resource<Movie>>?

    fun getFavoriteTvShows(): LiveData<Resource<List<TvShow>>>?

    fun getFavoriteTvShow(idTvShow: Int): LiveData<Resource<TvShow>>?

    fun setIsFavoriteMovie(movie: Movie, isFavorite: Boolean)

    fun setIsFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean)

    fun insertFavoriteMovies(movies: List<Movie>)

    fun insertFavoriteTvShows(tvShows: List<TvShow>)
}