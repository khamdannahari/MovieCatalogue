package com.aranirahan.moviecatalogue.ui.detailtvshow

import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.TvShow
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DetailTvShowViewModelTest{

    private var vmTvShow: DetailTvShowViewModel? = null
    private var tvShow: TvShow? = null

    @Before
    fun setUp() {
        vmTvShow = DetailTvShowViewModel()
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
        assertEquals(tvShow?.id, vmTvShow?.detailTvShow(1)?.id)
        assertEquals(tvShow?.title, vmTvShow?.detailTvShow(1)?.title)
        assertEquals(tvShow?.description, vmTvShow?.detailTvShow(1)?.description)
        assertEquals(tvShow?.image, vmTvShow?.detailTvShow(1)?.image)
        assertEquals(tvShow?.date, vmTvShow?.detailTvShow(1)?.date)
    }
}