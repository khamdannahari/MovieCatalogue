package com.aranirahan.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.aranirahan.moviecatalogue.data.source.locale.LocaleRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.data.source.remote.RemoteRepository2
import com.aranirahan.moviecatalogue.utils.FakeDataDummy
import com.aranirahan.moviecatalogue.utils.InstantAppExecutors
import com.aranirahan.moviecatalogue.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    private val instantAppExecutors = mock(InstantAppExecutors::class.java)
    private val dataRepository = FakeDataRepository(local, remote, instantAppExecutors)

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
        assertNotNull(result)
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
        assertEquals(tvShowResponse?.id, result.id)
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

    @Test
    fun getFavoriteMovies(){
        val movies = MutableLiveData<List<Movie>>()
        movies.value = FakeDataDummy.generateDummyMovies()

        `when`(local.getFavoriteMovies()).thenReturn(movies)

        val result = LiveDataTestUtil.getValue(dataRepository.getFavoriteMovies())

        verify(local).getFavoriteMovies()
        assertNotNull(result)
    }

    @Test
    fun getFavoriteMovie(){
        val movie = MutableLiveData<Movie>()
        movie.value = FakeDataDummy.getMovieById(idMovieResponse)

        `when`(local.getFavoriteMovie(idMovieResponse)).thenReturn(movie)

        val result = LiveDataTestUtil.getValue(dataRepository.getFavoriteMovie(idMovieResponse))

        verify(local).getFavoriteMovie(idMovieResponse)
        assertNotNull(result)
    }

    @Test
    fun getFavoriteTvShows(){
        val tvShows = MutableLiveData<List<TvShow>>()
        tvShows.value = FakeDataDummy.generateDummyTvShows()

        `when`(local.getFavoriteTvShows()).thenReturn(tvShows)

        val result = LiveDataTestUtil.getValue(dataRepository.getFavoriteTvShows())

        verify(local).getFavoriteTvShows()
        assertNotNull(result)
    }

    @Test
    fun getFavoriteTvShow(){
        val tvShow = MutableLiveData<TvShow>()
        tvShow.value = FakeDataDummy.getTvShowById(idMovieResponse)

        `when`(local.getFavoriteTvShow(idMovieResponse)).thenReturn(tvShow)

        val result = LiveDataTestUtil.getValue(dataRepository.getFavoriteTvShow(idMovieResponse))

        verify(local).getFavoriteTvShow(idMovieResponse)
        assertNotNull(result)
    }
}