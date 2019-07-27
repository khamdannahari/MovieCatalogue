package com.aranirahan.moviecatalogue.data.source.remote

import android.os.Handler
import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse
import com.aranirahan.moviecatalogue.utils.EspressoIdlingResource
import com.aranirahan.moviecatalogue.utils.JsonHelper

open class RemoteRepository private constructor(private val jsonHelper: JsonHelper) {

    fun getMovieResponseList(callback: GetMoviesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onMoviesReceived(jsonHelper.loadMovieResponseList())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getMovieResponse(idMovie: Int, callback: GetMovieCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onMovieReceived(jsonHelper.loadMovieResponse(idMovie))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }


    fun getTvShowResponseList(callback: GetTvShowsCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onTvShowsReceived(jsonHelper.loadTvShowResponseList())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getTvShowResponse(idMovie: Int, callback: GetTvShowCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onTvShowReceived(jsonHelper.loadTvShowResponse(idMovie))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface GetMoviesCallback {
        fun onMoviesReceived(movieResponseList: List<MovieResponse>)
        fun onDataNotAvailable()
    }

    interface GetMovieCallback {
        fun onMovieReceived(movieResponse: MovieResponse)
        fun onDataNotAvailable()
    }

    interface GetTvShowsCallback {
        fun onTvShowsReceived(tvShowResponseList: List<TvShowResponse>)
        fun onDataNotAvailable()
    }

    interface GetTvShowCallback {
        fun onTvShowReceived(tvShowResponse: TvShowResponse)
        fun onDataNotAvailable()
    }

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE as RemoteRepository
        }
    }
}



