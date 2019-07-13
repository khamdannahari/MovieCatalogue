package com.aranirahan.moviecatalogue.data.source

import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository
import java.util.ArrayList


open class DataRepository private constructor(
    private val localeRepository: LocaleRepository,
    private val remoteRepository: RemoteRepository
) : DataSource {
    override fun getMovies(): List<Movie> {
        val movieResponseList = remoteRepository.getMovieResponseList()
        val movies = ArrayList<Movie>()
        for (i in movieResponseList.indices) {
            val response = movieResponseList[i]
            val movie = Movie(
                id = response.id,
                title = response.title,
                description = response.description,
                date = response.date,
                image = response.image
            )

            movies.add(movie)
        }
        return movies
    }

    override fun getMovie(idMovie: Int): Movie {
        val movieResponse = remoteRepository.getMovieResponse(idMovie)
        return Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            description = movieResponse.description,
            date = movieResponse.date,
            image = movieResponse.image
        )
    }

    override fun getTvShows(): List<TvShow> {
        val tvShowResponseList = remoteRepository.getTvShowResponseList()
        val tvShows = ArrayList<TvShow>()
        for (i in tvShowResponseList.indices) {
            val response = tvShowResponseList[i]
            val tvShow = TvShow(
                id = response.id,
                title = response.title,
                description = response.description,
                date = response.date,
                image = response.image
            )

            tvShows.add(tvShow)
        }
        return tvShows
    }

    override fun getTvShow(idTvShow: Int): TvShow {
        val tvShowResponse = remoteRepository.getTvShowResponse(idTvShow)
        return TvShow(
            id = tvShowResponse.id,
            title = tvShowResponse.title,
            description = tvShowResponse.description,
            date = tvShowResponse.date,
            image = tvShowResponse.image
        )
    }

    companion object {

        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(localeRepository: LocaleRepository, remoteData: RemoteRepository): DataRepository? {
            if (INSTANCE == null) {
                synchronized(DataRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = DataRepository(localeRepository, remoteData)
                    }
                }
            }
            return INSTANCE
        }
    }
}