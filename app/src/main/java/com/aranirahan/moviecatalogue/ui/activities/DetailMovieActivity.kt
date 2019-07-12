package com.aranirahan.moviecatalogue.ui.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.Movie
import com.aranirahan.moviecatalogue.viewmodel.DetailMovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private val vmDetailMovie by lazy {
        ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)
    }

    private val idMovie by lazy { intent.getIntExtra(ID_MOVIE,0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        initView(vmDetailMovie.detailMovie(idMovie))
    }

    private fun initView(movie:Movie?) {
        movie?.image?.let { Picasso.get().load(it).into(iv_detail_movie) }
        tv_title_detail_movie.text = movie?.title
        tv_desc_detail_movie.text = movie?.description
    }

    companion object {
        const val ID_MOVIE = "ID_MOVIE"
    }
}
