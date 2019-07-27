package com.aranirahan.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.data.source.remote.ApiResponse
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository2
import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse
import com.aranirahan.moviecatalogue.utils.AppExecutors
import com.aranirahan.moviecatalogue.vo.Resource
import java.util.*


open class DataRepository constructor(
    private val localeRepository: LocaleRepository,
    private val remoteRepository2: RemoteRepository2,
    val appExecutors: AppExecutors
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

    override fun getFavoriteMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {

            override fun loadFromDB(): LiveData<List<Movie>> {
                return localeRepository.getFavoriteMovies()
            }

            override fun shouldFetch(data: List<Movie>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<MovieResponse>) {

            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(idMovie: Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<Movie> {
                return localeRepository.getFavoriteMovie(idMovie)
            }

            override fun shouldFetch(data: Movie): Boolean? {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> ?{
                return null
            }

            override fun saveCallResult(data: MovieResponse) {

            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>(appExecutors) {

            override fun loadFromDB(): LiveData<List<TvShow>> {
                return localeRepository.getFavoriteTvShows()
            }

            override fun shouldFetch(data: List<TvShow>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<TvShowResponse>) {

            }
        }.asLiveData()
    }

    override fun getFavoriteTvShow(idTvShow: Int): LiveData<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShowResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<TvShow> {
                return localeRepository.getFavoriteTvShow(idTvShow)
            }

            override fun shouldFetch(data: TvShow): Boolean? {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>>? {
                return null
            }

            override fun saveCallResult(data: TvShowResponse) {

            }
        }.asLiveData()
    }

    override fun setIsFavoriteMovie(movie: Movie, isFavorite: Boolean) {
        val runnable = { localeRepository.setIsFavoriteMovie(movie, isFavorite) }
        appExecutors.diskIO().execute(runnable)
    }

    override fun setIsFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean) {
        val runnable = { localeRepository.setIsFavoriteTvShow(tvShow, isFavorite) }
        appExecutors.diskIO().execute(runnable)
    }

    override fun insertFavoriteMovies(movies: List<Movie>) {
        val runnable = {
            if(localeRepository.getFavoriteMovies().value.isNullOrEmpty()){
                localeRepository.insertFavoriteMovies(movies)
            }
        }
        appExecutors.diskIO().execute(runnable)
    }

    override fun insertFavoriteTvShows(tvShows: List<TvShow>) {
        val runnable = {
            if(localeRepository.getFavoriteTvShows().value.isNullOrEmpty()){
                localeRepository.insertFavoriteTvShows(tvShows)
            }
        }
        appExecutors.diskIO().execute(runnable)
    }

    companion object {

        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(
            localeRepository: LocaleRepository,
            remoteData: RemoteRepository2,
            appExecutors: AppExecutors
        ): DataRepository? {
            if (INSTANCE == null) {
                synchronized(DataRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = DataRepository(localeRepository, remoteData, appExecutors)
                    }
                }
            }
            return INSTANCE
        }
    }
}