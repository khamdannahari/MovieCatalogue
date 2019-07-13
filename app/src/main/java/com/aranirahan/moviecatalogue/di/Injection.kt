package com.aranirahan.moviecatalogue.di

import android.app.Application
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.remote.JsonHelper
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository

object Injection {
    fun provideMovieRepository(application: Application): DataRepository? {

        val localRepository = LocaleRepository()
        val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))

        return DataRepository.getInstance(localRepository, remoteRepository)
    }
}