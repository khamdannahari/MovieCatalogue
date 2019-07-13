package com.aranirahan.moviecatalogue.ui.main

import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow

class MainViewModel(dataRepository: DataRepository) : ViewModel() {

    val movies: List<Movie> = dataRepository.getMovies()
    val tvShows: List<TvShow> = dataRepository.getTvShows()

}