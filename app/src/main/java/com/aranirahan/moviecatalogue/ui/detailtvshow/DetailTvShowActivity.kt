package com.aranirahan.moviecatalogue.ui.detailtvshow


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.goGone
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieViewModel
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailTvShowActivity : AppCompatActivity() {

    private val vmDetailTvShow by lazy { obtainViewModel(this) }

    private val idTvShow by lazy { intent.getIntExtra(ID_TV_SHOW, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        vmDetailTvShow.detailTvShow(idTvShow).observe(this, Observer {
            progress_circular.goGone()
            initView(it)
        })

    }

    private fun initView(tvShow: TvShow?) {
        tvShow?.image?.let { Picasso.get().load(it).into(iv_detail_movie) }
        tv_title_detail_movie.text = tvShow?.title
        tv_desc_detail_movie.text = tvShow?.description
    }

    private fun obtainViewModel(activity: FragmentActivity): DetailTvShowViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailTvShowViewModel::class.java)
    }

    companion object {
        const val ID_TV_SHOW = "ID_TV_SHOW"
    }
}
