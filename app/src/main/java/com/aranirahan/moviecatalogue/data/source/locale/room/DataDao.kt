package com.aranirahan.moviecatalogue.data.source.locale.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow


@Dao
interface DataDao {

//    @get:WorkerThread
//    @get:Query("SELECT * FROM movie")
//    val movies: LiveData<List<Movie>>
//
//    @get:WorkerThread
//    @get:Query("SELECT * FROM tvShow")
//    val tvShows: LiveData<List<TvShow>>
//
    @Transaction
    @Query("SELECT * FROM movie WHERE id = :idMovie")
    fun getFavoriteMovieById(idMovie: Int): LiveData<Movie>

    @Transaction
    @Query("SELECT * FROM tvShow WHERE id = :idTvShow")
    fun getFavoriteTvShowById(idTvShow: Int): LiveData<TvShow>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovies(movies: List<Movie>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTvShow(tvShows: List<TvShow>): LongArray


    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateFavoriteMovie(movie: Movie): Int

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateFavoriteTvShow(tvShow: TvShow): Int


    @WorkerThread
    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<Movie>>

    @WorkerThread
    @Query("SELECT * FROM tvShow where isFavorite = 1")
    fun getFavoriteTvShows(): LiveData<List<TvShow>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovieAsPaged(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tvshow where isFavorite = 1")
    fun getFavoriteTvShowAsPaged(): DataSource.Factory<Int, TvShow>
}