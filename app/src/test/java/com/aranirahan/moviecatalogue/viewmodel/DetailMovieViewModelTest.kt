package com.aranirahan.moviecatalogue.viewmodel

import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.Movie
import org.junit.After
import org.junit.Assert
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
        Assert.assertEquals(movie?.id, vmDetailMovie?.detailMovie(1)?.id)
        Assert.assertEquals(movie?.title, vmDetailMovie?.detailMovie(1)?.title)
        Assert.assertEquals(movie?.director, vmDetailMovie?.detailMovie(1)?.director)
        Assert.assertEquals(movie?.description, vmDetailMovie?.detailMovie(1)?.description)
        Assert.assertEquals(movie?.image, vmDetailMovie?.detailMovie(1)?.image)
        Assert.assertEquals(movie?.date, vmDetailMovie?.detailMovie(1)?.date)
    }
}