package com.aranirahan.moviecatalogue.data.source.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aranirahan.moviecatalogue.R

@Entity
data class Movie(
        @PrimaryKey
        val id : Int = 0,
        val title : String = "",
        val director: String = "",
        val description : String = "",
        val image : Int = R.drawable.ic_image_black_24dp,
        val date : String = "",
        val rating : String = "",
        var isFavorite:Boolean=false
)