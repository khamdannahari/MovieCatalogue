package com.aranirahan.moviecatalogue.ui.detailmovie

import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.ui.main.utils.FakeDataDummy
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class DetailMovieViewModelTest{

    private var vmDetailMovie: DetailMovieViewModel? = null
    private lateinit var movie: Movie
    private val dataRepository = mock(DataRepository::class.java)

    @Before
    fun setUp() {
        vmDetailMovie = DetailMovieViewModel(dataRepository)
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
        `when`(dataRepository.getMovie(movie.id)).thenReturn(FakeDataDummy.getMovieById(movie.id))
        assertEquals(movie.id, vmDetailMovie?.detailMovie(1)?.id)
        assertEquals(movie.title, vmDetailMovie?.detailMovie(1)?.title)
        assertEquals(movie.director, vmDetailMovie?.detailMovie(1)?.director)
        assertEquals(movie.description, vmDetailMovie?.detailMovie(1)?.description)
        assertEquals(movie.image, vmDetailMovie?.detailMovie(1)?.image)
        assertEquals(movie.date, vmDetailMovie?.detailMovie(1)?.date)
    }
}