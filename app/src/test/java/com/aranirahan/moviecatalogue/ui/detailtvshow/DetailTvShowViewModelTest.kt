package com.aranirahan.moviecatalogue.ui.detailtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.DataRepository
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.utils.FakeDataDummy
import com.aranirahan.moviecatalogue.utils.LiveDataTestUtil
import com.aranirahan.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.*
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

    @Test
    fun favoriteMovie(){
        val tvShow = MutableLiveData<Resource<TvShow>>()
        tvShow.value = Resource.success(FakeDataDummy.getTvShowById(this.tvShow.id))

        `when`(dataRepository.getFavoriteTvShow(this.tvShow.id)).thenReturn(tvShow)

        val observer = mock(Observer::class.java)

        vmTvShow?.favoriteTvShow?.observeForever(observer as Observer<Resource<TvShow>>)

        val result = LiveDataTestUtil.getValue(dataRepository.getFavoriteTvShow(this.tvShow.id))
        Assert.assertNotNull(result)
    }
}