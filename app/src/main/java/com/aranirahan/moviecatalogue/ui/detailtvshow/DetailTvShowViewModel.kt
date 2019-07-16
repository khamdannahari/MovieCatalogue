package com.aranirahan.moviecatalogue.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow

class DetailTvShowViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun detailTvShow(id: Int): LiveData<TvShow> = dataRepository.getTvShow(id)

}