package com.aranirahan.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository2
import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse
import java.util.*


open class FakeDataRepository(
    private val localeRepository: LocaleRepository,
    private val remoteRepository2: RemoteRepository2
) : DataSource {

    override fun getMovies(): LiveData<List<Movie>> {
        val moviesMutable = MutableLiveData<List<Movie>>()

        remoteRepository2.getMovieResponseList(object : RemoteRepository2.GetMoviesCallback {
            override fun onMoviesReceived(movieResponseList: List<MovieResponse>) {
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
                moviesMutable.postValue(movies)
            }

            override fun onDataNotAvailable() {
                //
            }

        })
        return moviesMutable
    }

    override fun getMovie(idMovie: Int): LiveData<Movie> {
        val movieMutable = MutableLiveData<Movie>()

        remoteRepository2.getMovieResponse(idMovie, object : RemoteRepository2.GetMovieCallback {
            override fun onMovieReceived(movieResponse: MovieResponse) {
                val movie = Movie(
                    id = movieResponse.id,
                    title = movieResponse.title,
                    description = movieResponse.description,
                    date = movieResponse.date,
                    image = movieResponse.image
                )
                movieMutable.postValue(movie)
            }

            override fun onDataNotAvailable() {
                //
            }

        })
        return movieMutable
    }


    override fun getTvShows(): LiveData<List<TvShow>> {
        val moviesMutable = MutableLiveData<List<TvShow>>()

        remoteRepository2.getTvShowResponseList(object : RemoteRepository2.GetTvShowsCallback {
            override fun onTvShowsReceived(tvShowResponseList: List<TvShowResponse>) {
                val tvShows = ArrayList<TvShow>()
                for (i in tvShowResponseList.indices) {
                    val response = tvShowResponseList[i]
                    val movie = TvShow(
                        id = response.id,
                        title = response.title,
                        description = response.description,
                        date = response.date,
                        image = response.image
                    )
                    tvShows.add(movie)
                }
                moviesMutable.postValue(tvShows)
            }

            override fun onDataNotAvailable() {
                //
            }

        })
        return moviesMutable
    }

    override fun getTvShow(idTvShow: Int): LiveData<TvShow> {
        val tvShowMutable = MutableLiveData<TvShow>()

        remoteRepository2.getTvShowResponse(idTvShow, object : RemoteRepository2.GetTvShowCallback {
            override fun onTvShowReceived(tvShowResponse: TvShowResponse) {
                val tvShow = TvShow(
                    id = tvShowResponse.id,
                    title = tvShowResponse.title,
                    description = tvShowResponse.description,
                    date = tvShowResponse.date,
                    image = tvShowResponse.image
                )
                tvShowMutable.postValue(tvShow)
            }

            override fun onDataNotAvailable() {
                //
            }

        })
        return tvShowMutable
    }

    companion object {

        @Volatile
        private var INSTANCE: FakeDataRepository? = null

        fun getInstance(
            localeRepository: LocaleRepository,
            remoteData: RemoteRepository2
        ): FakeDataRepository? {
            if (INSTANCE == null) {
                synchronized(FakeDataRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FakeDataRepository(localeRepository, remoteData)
                    }
                }
            }
            return INSTANCE
        }
    }
}