package com.aranirahan.moviecatalogue.ui.detailmovie

import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.Movie
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DetailMovieViewModelTest{

    private var vmDetailMovie: DetailMovieViewModel? = null
    private var movie: Movie? = null

    @Before
    fun setUp() {
        vmDetailMovie = DetailMovieViewModel()
        movie = Movie(
            1,
            "Alita : Battle Angel",
            "Robert Rodriguez",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            R.drawable.poster_alita,
            "2019",
            "67"
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun detailMovie() {
        assertEquals(movie?.id, vmDetailMovie?.detailMovie(1)?.id)
        assertEquals(movie?.title, vmDetailMovie?.detailMovie(1)?.title)
        assertEquals(movie?.director, vmDetailMovie?.detailMovie(1)?.director)
        assertEquals(movie?.description, vmDetailMovie?.detailMovie(1)?.description)
        assertEquals(movie?.image, vmDetailMovie?.detailMovie(1)?.image)
        assertEquals(movie?.date, vmDetailMovie?.detailMovie(1)?.date)
    }
}