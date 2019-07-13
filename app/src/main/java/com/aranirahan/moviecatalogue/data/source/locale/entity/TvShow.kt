package com.aranirahan.moviecatalogue.data.source.locale.entity

import com.aranirahan.moviecatalogue.R

data class TvShow(
        val id: Int = 0,
        val title: String = "",
        val description: String = "",
        val image: Int = R.drawable.ic_image_black_24dp,
        val date: String = "",
        val rating: String = ""
)