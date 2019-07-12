package com.aranirahan.moviecatalogue.model

import android.os.Parcelable
import com.aranirahan.moviecatalogue.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        val id : Int = 0,
        val title : String = "",
        val director: String = "",
        val description : String = "",
        val image : Int = R.drawable.ic_image_black_24dp,
        val date : String = "",
        val rating : String = ""
) : Parcelable