package com.aranirahan.moviecatalogue.data.source.locale

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.data.source.locale.room.DataDao

open class LocaleRepository constructor(private val mDataDao: DataDao) {

    fun getFavoriteMovies(): LiveData<List<Movie>> = mDataDao.getFavoriteMovies()

    fun getFavoriteMovie(idMovie: Int): LiveData<Movie> = mDataDao.getFavoriteMovieById(idMovie)

    fun insertFavoriteMovies(movies:List<Movie>){
        mDataDao.insertFavoriteMovies(movies)
    }

    fun setIsFavoriteMovie(movie: Movie, isFavorite:Boolean){
        movie.isFavorite = isFavorite
        mDataDao.updateFavoriteMovie(movie)
    }


    fun getFavoriteTvShows(): LiveData<List<TvShow>> = mDataDao.getFavoriteTvShows()

    fun getFavoriteTvShow(idTvShow: Int): LiveData<TvShow> = mDataDao.getFavoriteTvShowById(idTvShow)

    fun insertFavoriteTvShows(tvShows:List<TvShow>){
        mDataDao.insertFavoriteTvShow(tvShows)
    }

    fun setIsFavoriteTvShow(tvShow: TvShow, isFavorite:Boolean){
        tvShow.isFavorite = isFavorite
        mDataDao.updateFavoriteTvShow(tvShow)
    }

    fun getFavoriteMovieAsPaged(): DataSource.Factory<Int, Movie> {
        return mDataDao.getFavoriteMovieAsPaged()
    }

    fun getFavoriteTvShowAsPaged(): DataSource.Factory<Int, TvShow> {
        return mDataDao.getFavoriteTvShowAsPaged()
    }

    companion object {

        private var INSTANCE: LocaleRepository? = null

        fun getInstance(academyDao: DataDao): LocaleRepository {
            if (INSTANCE == null) {
                INSTANCE = LocaleRepository(academyDao)
            }
            return INSTANCE as LocaleRepository
        }
    }
}