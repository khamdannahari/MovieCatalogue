package com.aranirahan.moviecatalogue.di

import android.app.Application
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.locale.room.DataDatabase
import com.aranirahan.moviecatalogue.utils.JsonHelper
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository
import com.aranirahan.moviecatalogue.utils.AppExecutors

//object Injection {
//    fun provideMovieRepository(application: Application): DataRepository? {
//
//        val localRepository = LocaleRepository()
//        val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))
//
//        return DataRepository.getInstance(localRepository, remoteRepository)
//    }
//}

object Injection {
    fun provideMovieRepository(application: Application): DataRepository? {

        val database = DataDatabase.getInstance(application)

        val localRepository = LocaleRepository.getInstance(database.dataDao())
        val remoteRepository = RemoteRepository.getInstance(
            JsonHelper(
                application
            )
        )
        val appExecutors = AppExecutors()

        return DataRepository.getInstance(localRepository, remoteRepository, appExecutors)
    }
}