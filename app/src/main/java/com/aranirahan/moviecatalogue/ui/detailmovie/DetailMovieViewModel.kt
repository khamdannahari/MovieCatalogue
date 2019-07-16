package com.aranirahan.moviecatalogue.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie

class DetailMovieViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun detailMovie(id: Int): LiveData<Movie> = dataRepository.getMovie(id)

}