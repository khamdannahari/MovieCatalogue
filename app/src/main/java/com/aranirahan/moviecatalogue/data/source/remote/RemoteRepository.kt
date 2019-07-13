package com.aranirahan.moviecatalogue.data.source.remote

import android.os.Handler
import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse

class RemoteRepository private constructor(private val jsonHelper: JsonHelper) {

    fun getMovieResponseList(callback: GetMoviesCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onMoviesReceived(jsonHelper.loadMovieResponseList()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getMovieResponse(idMovie: Int, callback: GetMovieCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onMovieReceived(jsonHelper.loadMovieResponse(idMovie)) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }


    fun getTvShowResponseList(callback: GetTvShowsCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onTvShowsReceived(jsonHelper.loadTvShowResponseList()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getTvShowResponse(idMovie: Int, callback: GetTvShowCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onTvShowReceived(jsonHelper.loadTvShowResponse(idMovie)) },
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
            return INSTANCE!!
        }
    }
}



