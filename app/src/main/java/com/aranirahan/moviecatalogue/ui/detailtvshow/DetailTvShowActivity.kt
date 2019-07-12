package com.aranirahan.moviecatalogue.ui.detailtvshow


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.TvShow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailTvShowActivity : AppCompatActivity() {

    private val vmDetailTvShow by lazy {
        ViewModelProviders.of(this).get(DetailTvShowViewModel::class.java)
    }

    private val idTvShow by lazy { intent.getIntExtra(ID_TV_SHOW,0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        initView(vmDetailTvShow.detailTvShow(idTvShow))
    }

    private fun initView(tvShow:TvShow?) {
        tvShow?.image?.let { Picasso.get().load(it).into(iv_detail_movie) }
        tv_title_detail_movie.text = tvShow?.title
        tv_desc_detail_movie.text = tvShow?.description
    }

    companion object {
        const val ID_TV_SHOW = "ID_TV_SHOW"
    }
}
