package com.aranirahan.moviecatalogue.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow

class DetailTvShowViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun detailTvShow(id: Int): LiveData<TvShow> = dataRepository.getTvShow(id)

    fun setIsFavoriteTvShow() {
        favoriteTvShow.value?.data?.let {
            val newState = !it.isFavorite
            dataRepository.setIsFavoriteTvShow(it, newState)
        }
    }

    val idTvShow = MutableLiveData<Int>()

    val favoriteTvShow = Transformations.switchMap(idTvShow) { idTvShow ->
        dataRepository.getFavoriteTvShow(idTvShow)
    }

}