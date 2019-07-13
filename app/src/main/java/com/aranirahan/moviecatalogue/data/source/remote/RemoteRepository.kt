package com.aranirahan.moviecatalogue.data.source.remote

import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse

class RemoteRepository private constructor(private val jsonHelper: JsonHelper) {

    fun getMovieResponseList(): List<MovieResponse> = jsonHelper.loadMovieResponseList()
    fun getMovieResponse(idMovie: Int): MovieResponse = jsonHelper.loadMovieResponse(idMovie)
    fun getTvShowResponseList(): List<TvShowResponse> = jsonHelper.loadTvShowResponseList()
    fun getTvShowResponse(idMovie: Int): TvShowResponse = jsonHelper.loadTvShowResponse(idMovie)

    companion object {

        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE!!
        }
    }
}


