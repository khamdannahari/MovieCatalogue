package com.aranirahan.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository2
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
    private val remote = mock(RemoteRepository2::class.java)
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

        //sementara class RemoteRepository memakai java dulu, yang kotlin blm solved kak hehe

        doAnswer {
            val callback = it.arguments[0] as RemoteRepository2.GetMoviesCallback
            callback.onMoviesReceived(movieResponseList)
            null
        }.`when`(remote).getMovieResponseList(any(RemoteRepository2.GetMoviesCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepository.getMovies())

        verify(
            remote,
            times(1)
        ).getMovieResponseList(any(RemoteRepository2.GetMoviesCallback::class.java))

        assertEquals(movieResponseList.size, result.size)
    }

    @Test
    fun getMovieResponse() {

        doAnswer {
            val callback = it.arguments[1] as RemoteRepository2.GetMovieCallback
            movieResponse?.let { it1 -> callback.onMovieReceived(it1) }
            null
        }.`when`(remote).getMovieResponse(
            eq(idMovieResponse),
            any(RemoteRepository2.GetMovieCallback::class.java)
        )

        val result = LiveDataTestUtil.getValue(dataRepository.getMovie(idMovieResponse))
        verify(
            remote,
            times(1)
        ).getMovieResponse(
            eq(idMovieResponse),
            any(RemoteRepository2.GetMovieCallback::class.java)
        )
        assertEquals(tvShowResponse?.id, 1)
    }

    @Test
    fun getTvShowResponseList() {

        doAnswer {
            val callback = it.arguments[0] as RemoteRepository2.GetTvShowsCallback
            callback.onTvShowsReceived(tvShowResponseList)
            null
        }.`when`(remote)
            .getTvShowResponseList(any(RemoteRepository2.GetTvShowsCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepository.getTvShows())

        verify(
            remote,
            times(1)
        ).getTvShowResponseList(any(RemoteRepository2.GetTvShowsCallback::class.java))

        assertEquals(movieResponseList.size, result.size)
    }

    @Test
    fun getTvShowResponse() {

        doAnswer {
            val callback = it.arguments[1] as RemoteRepository2.GetTvShowCallback
            tvShowResponse?.let { it1 -> callback.onTvShowReceived(it1) }
            null
        }.`when`(remote).getTvShowResponse(
            eq(idTvShowResponse),
            any(RemoteRepository2.GetTvShowCallback::class.java)
        )

        val result = LiveDataTestUtil.getValue(dataRepository.getTvShow(idTvShowResponse))

        verify(
            remote,
            times(1)
        ).getTvShowResponse(eq(idTvShowResponse), any(RemoteRepository2.GetTvShowCallback::class.java))

        assertEquals(tvShowResponse?.id, result.id)
    }
}