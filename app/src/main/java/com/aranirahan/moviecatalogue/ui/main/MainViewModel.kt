package com.aranirahan.moviecatalogue.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.vo.Resource

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val movies: LiveData<List<Movie>> = dataRepository.getMovies()
    val tvShows: LiveData<List<TvShow>> = dataRepository.getTvShows()

    fun insertFavoriteMovies(movies: List<Movie>) {
        dataRepository.insertFavoriteMovies(movies)
    }

    fun insertFavoriteTvShows(tvShows: List<TvShow>){
        dataRepository.insertFavoriteTvShows(tvShows)
    }

    val favoriteMovies: LiveData<Resource<List<Movie>>> = dataRepository.getFavoriteMovies()
    val favoriteTvShows: LiveData<Resource<List<TvShow>>> = dataRepository.getFavoriteTvShows()

}