package com.aranirahan.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository
import com.aranirahan.moviecatalogue.ui.main.utils.FakeDataDummy
import com.aranirahan.moviecatalogue.ui.main.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class DataRepositoryTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = mock(LocaleRepository::class.java)
    private val remote = mock(RemoteRepository::class.java)
    private val dataRepository = FakeDataRepository(local, remote)

    private val movieResponseList = FakeDataDummy.generateRemoteDummyMovies()
    private val idMovieResponse = movieResponseList[0].id
    private val movieResponse = FakeDataDummy.getRemoteMovieById(idMovieResponse)

    private val tvShowResponseList = FakeDataDummy.generateRemoteDummyTvShows()
    private val idTvShowResponse = tvShowResponseList[0].id
    private val tvShowResponse = FakeDataDummy.getRemoteTvShowById(idTvShowResponse)

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun getMovieResponseList() {

        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.GetMoviesCallback
            callback.onMoviesReceived(movieResponseList)
            null
        }.`when`(remote).getMovieResponseList(any(RemoteRepository.GetMoviesCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepository.getMovies())

        verify(
            remote,
            times(1)
        ).getMovieResponseList(any(RemoteRepository.GetMoviesCallback::class.java))

        assertEquals(movieResponseList.size, result.size)
    }

    @Test
    fun getMovieResponse() {

        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.GetMovieCallback
            movieResponse?.let { it1 -> callback.onMovieReceived(it1) }
            null
        }.`when`(remote).getMovieResponse(eq(idMovieResponse), any(RemoteRepository.GetMovieCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepository.getMovie(idMovieResponse))

        verify(
            remote,
            times(1)
        ).getMovieResponse(eq(idMovieResponse), any(RemoteRepository.GetMovieCallback::class.java))

        assertEquals(movieResponse?.id, result.id)
    }

    @Test
    fun getTvShowResponseList() {

        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.GetTvShowsCallback
            callback.onTvShowsReceived(tvShowResponseList)
            null
        }.`when`(remote).getTvShowResponseList(any(RemoteRepository.GetTvShowsCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepository.getTvShows())

        verify(
            remote,
            times(1)
        ).getTvShowResponseList(any(RemoteRepository.GetTvShowsCallback::class.java))

        assertEquals(movieResponseList.size, result.size)
    }

    @Test
    fun getTvShowResponse() {

        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.GetTvShowCallback
            tvShowResponse?.let { it1 -> callback.onTvShowReceived(it1) }
            null
        }.`when`(remote).getTvShowResponse(eq(idTvShowResponse), any(RemoteRepository.GetTvShowCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepository.getTvShow(idTvShowResponse))

        verify(
            remote,
            times(1)
        ).getTvShowResponse(eq(idTvShowResponse), any(RemoteRepository.GetTvShowCallback::class.java))

        assertEquals(tvShowResponse?.id, result.id)
    }
}