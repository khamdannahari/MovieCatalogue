package com.aranirahan.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.DataDummy
import com.aranirahan.moviecatalogue.model.Movie

class DetailMovieViewModel: ViewModel(){

    fun detailMovie(id: Int) : Movie? = DataDummy.getMovieById(id)

}