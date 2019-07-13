package com.aranirahan.moviecatalogue.data.source.remote.response

import com.aranirahan.moviecatalogue.R


data class MovieResponse(
    val id : Int = 0,
    val title : String = "",
    val director: String = "",
    val description : String = "",
    val image : Int = R.drawable.ic_image_black_24dp,
    val date : String = "",
    val rating : String = ""
)