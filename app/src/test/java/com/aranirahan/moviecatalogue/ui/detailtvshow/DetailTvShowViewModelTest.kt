package com.aranirahan.moviecatalogue.ui.detailtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.ui.main.utils.FakeDataDummy
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class DetailTvShowViewModelTest{

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var vmTvShow: DetailTvShowViewModel? = null
    private lateinit var tvShow: TvShow
    private val dataRepository = mock(DataRepository::class.java)

    @Before
    fun setUp() {
        vmTvShow = DetailTvShowViewModel(dataRepository)
        tvShow = TvShow(
            1,
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            R.drawable.poster_arrow,
            "2012",
            "58"
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun detailTvShow() {
//        `when`(dataRepository.getTvShow(tvShow.id)).thenReturn(FakeDataDummy.getTvShowById(tvShow.id))
//        assertEquals(tvShow.id, vmTvShow?.detailTvShow(1)?.id)
//        assertEquals(tvShow.title, vmTvShow?.detailTvShow(1)?.title)
//        assertEquals(tvShow.description, vmTvShow?.detailTvShow(1)?.description)
//        assertEquals(tvShow.image, vmTvShow?.detailTvShow(1)?.image)
//        assertEquals(tvShow.date, vmTvShow?.detailTvShow(1)?.date)

        val tvShowMutable = MutableLiveData<TvShow>()
        tvShowMutable.value = FakeDataDummy.getTvShowById(tvShow.id)
        `when`(dataRepository.getTvShow(tvShow.id)).thenReturn(tvShowMutable)
        val observer = mock(Observer::class.java)
        vmTvShow?.detailTvShow(tvShow.id)?.observeForever(observer as Observer<TvShow>)
        verify(dataRepository).getTvShow(tvShow.id)
        assertEquals(tvShow.id, vmTvShow?.detailTvShow(tvShow.id)?.value?.id)
        assertEquals(tvShow.title, vmTvShow?.detailTvShow(tvShow.id)?.value?.title)
        assertEquals(tvShow.description, vmTvShow?.detailTvShow(tvShow.id)?.value?.description)
        assertEquals(tvShow.image, vmTvShow?.detailTvShow(tvShow.id)?.value?.image)
        assertEquals(tvShow.date, vmTvShow?.detailTvShow(tvShow.id)?.value?.date)
    }
}