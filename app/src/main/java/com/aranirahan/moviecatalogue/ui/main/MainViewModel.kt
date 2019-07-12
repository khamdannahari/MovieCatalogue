package com.aranirahan.moviecatalogue.ui.main

import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.DataDummy
import com.aranirahan.moviecatalogue.model.Movie
import com.aranirahan.moviecatalogue.model.TvShow

class MainViewModel : ViewModel() {

    val movies : List<Movie> = DataDummy.generateDummyMovies()
    val tvShows : List<TvShow> = DataDummy.generateDummyTvShow()

}