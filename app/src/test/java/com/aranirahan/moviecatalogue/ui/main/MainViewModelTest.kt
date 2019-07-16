package com.aranirahan.moviecatalogue.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.ui.main.utils.FakeDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class MainViewModelTest{

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private  var vmMain: MainViewModel ?= null
    private val dataRepository = mock(DataRepository::class.java)

    @Before
    fun setUp() {
        vmMain = MainViewModel(dataRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun movies() {

        val moviesMutable = MutableLiveData<List<Movie>>()
        moviesMutable.value = FakeDataDummy.generateDummyMovies()
        `when`(dataRepository.getMovies()).thenReturn(moviesMutable)
        val observer = mock(Observer::class.java)
        vmMain?.movies?.observeForever(observer as Observer<List<Movie>> )
        verify(dataRepository).getMovies()

    }

    @Test
    fun tvShows() {

        val tvShowsMutable = MutableLiveData<List<TvShow>>()
        tvShowsMutable.value = FakeDataDummy.generateDummyTvShows()
        `when`(dataRepository.getTvShows()).thenReturn(tvShowsMutable)
        val observer = mock(Observer::class.java)
        vmMain?.tvShows?.observeForever(observer as Observer<List<TvShow>> )
        verify(dataRepository).getTvShows()
    }
}