package com.aranirahan.moviecatalogue.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun detailMovie(id: Int): LiveData<Movie> {
        return dataRepository.getMovie(id)
    }

    fun setIsFavoriteMovie() {
        favoriteMovie.value?.data?.let {
            val newState = !it.isFavorite
            dataRepository.setIsFavoriteMovie(it, newState)
        }
    }

    val idMovie = MutableLiveData<Int>()

    fun setIdMovie(idMovie: Int){
        this.idMovie.value = idMovie
    }

    val favoriteMovie = Transformations.switchMap(idMovie) { idMovie ->
        dataRepository.getFavoriteMovie(idMovie)
    }

}