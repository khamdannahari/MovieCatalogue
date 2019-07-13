package com.aranirahan.moviecatalogue.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.di.Injection
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieViewModel
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowViewModel
import com.aranirahan.moviecatalogue.ui.main.MainViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val mDataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(mDataRepository) as T
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> DetailMovieViewModel(mDataRepository) as T
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> DetailTvShowViewModel(mDataRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Injection.provideMovieRepository(application)?.let { ViewModelFactory(it) }
                    }
                }
            }
            return INSTANCE
        }

    }
}