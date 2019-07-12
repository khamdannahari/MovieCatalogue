package com.aranirahan.moviecatalogue.ui.detailtvshow

import androidx.lifecycle.ViewModel
import com.aranirahan.moviecatalogue.data.DataDummy
import com.aranirahan.moviecatalogue.model.TvShow

class DetailTvShowViewModel: ViewModel(){

    fun detailTvShow(id: Int) : TvShow? = DataDummy.getTvShowById(id)

}