package com.aranirahan.moviecatalogue.ui.detailtvshow

import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.ui.main.utils.FakeDataDummy
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class DetailTvShowViewModelTest{

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
        `when`(dataRepository.getTvShow(tvShow.id)).thenReturn(FakeDataDummy.getTvShowById(tvShow.id))
        assertEquals(tvShow.id, vmTvShow?.detailTvShow(1)?.id)
        assertEquals(tvShow.title, vmTvShow?.detailTvShow(1)?.title)
        assertEquals(tvShow.description, vmTvShow?.detailTvShow(1)?.description)
        assertEquals(tvShow.image, vmTvShow?.detailTvShow(1)?.image)
        assertEquals(tvShow.date, vmTvShow?.detailTvShow(1)?.date)
    }
}