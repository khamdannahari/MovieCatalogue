package com.aranirahan.moviecatalogue.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.ui.main.utils.FakeDataDummy
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class DetailMovieViewModelTest{

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

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
//        `when`(dataRepository.getMovie(movie.id)).thenReturn(FakeDataDummy.getMovieById(movie.id))
//        assertEquals(movie.id, vmDetailMovie?.detailMovie(1)?.id)
//        assertEquals(movie.title, vmDetailMovie?.detailMovie(1)?.title)
//        assertEquals(movie.director, vmDetailMovie?.detailMovie(1)?.director)
//        assertEquals(movie.description, vmDetailMovie?.detailMovie(1)?.description)
//        assertEquals(movie.image, vmDetailMovie?.detailMovie(1)?.image)
//        assertEquals(movie.date, vmDetailMovie?.detailMovie(1)?.date)

        val tvShowMutable = MutableLiveData<Movie>()
        tvShowMutable.value = FakeDataDummy.getMovieById(movie.id)
        `when`(dataRepository.getMovie(movie.id)).thenReturn(tvShowMutable)
        val observer = mock(Observer::class.java)
        vmDetailMovie?.detailMovie(movie.id)?.observeForever(observer as Observer<Movie>)
        verify(dataRepository).getMovie(movie.id)
        assertEquals(movie.id, vmDetailMovie?.detailMovie(movie.id)?.value?.id)
        assertEquals(movie.title, vmDetailMovie?.detailMovie(movie.id)?.value?.title)
        assertEquals(movie.description, vmDetailMovie?.detailMovie(movie.id)?.value?.description)
        assertEquals(movie.image, vmDetailMovie?.detailMovie(movie.id)?.value?.image)
        assertEquals(movie.date, vmDetailMovie?.detailMovie(movie.id)?.value?.date)
    }
}