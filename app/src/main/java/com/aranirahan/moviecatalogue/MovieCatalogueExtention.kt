package com.aranirahan.moviecatalogue

import android.view.View

fun View.goGone(){
    if(this.visibility == View.VISIBLE){
        this.visibility = View.GONE
    }
}

fun View.goVisible(){
    if(this.visibility == View.GONE){
        this.visibility = View.VISIBLE
    }
}