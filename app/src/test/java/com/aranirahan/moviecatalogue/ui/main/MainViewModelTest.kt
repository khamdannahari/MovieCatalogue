package com.aranirahan.moviecatalogue.ui.main

import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.ui.main.utils.FakeDataDummy
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class MainViewModelTest{

    private  var vmMain: MainViewModel ?= null
    private val movieRepository = mock(DataRepository::class.java)

    @Before
    fun setUp() {
        vmMain = MainViewModel(movieRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun movies() {
        `when`(movieRepository.getMovies()).thenReturn(FakeDataDummy.generateDummyMovies())
        val movies = vmMain?.movies
        verify(movieRepository).getMovies()
        assertNotNull(movies)
        assertEquals(16, movieRepository.getMovies().size)
    }

    @Test
    fun tvShows() {
        `when`(movieRepository.getTvShows()).thenReturn(FakeDataDummy.generateDummyTvShows())
        val tvShow = vmMain?.tvShows
        verify(movieRepository).getTvShows()
        assertNotNull(tvShow)
        assertEquals(16, movieRepository.getTvShows().size)
    }
}