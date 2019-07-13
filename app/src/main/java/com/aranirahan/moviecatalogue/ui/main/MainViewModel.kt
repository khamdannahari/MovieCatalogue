package com.aranirahan.moviecatalogue.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow

class MainViewModel(dataRepository: DataRepository) : ViewModel() {

    val movies: LiveData<List<Movie>> = dataRepository.getMovies()
    val tvShows: LiveData<List<TvShow>> = dataRepository.getTvShows()

}