package com.aranirahan.moviecatalogue.ui.detailmovie

import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.DataDummy
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie

class DetailMovieViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun detailMovie(id: Int): Movie? = dataRepository.getMovie(id)

}