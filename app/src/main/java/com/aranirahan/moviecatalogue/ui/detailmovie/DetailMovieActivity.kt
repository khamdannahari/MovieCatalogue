package com.aranirahan.moviecatalogue.ui.detailmovie


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.utils.goGone
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private val vmDetailMovie by lazy { obtainViewModel(this) }

    private val idMovie by lazy { intent.getIntExtra(ID_MOVIE, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        vmDetailMovie.detailMovie(idMovie).observe(this, Observer {
            progress_circular.goGone()
            initView(it)
        })

    }

    private fun initView(movie: Movie?) {
        movie?.image?.let { Picasso.get().load(it).into(iv_detail_movie) }
        tv_title_detail_movie.text = movie?.title
        tv_desc_detail_movie.text = movie?.description
    }

    private fun obtainViewModel(activity: FragmentActivity): DetailMovieViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel::class.java)
    }

    companion object {
        const val ID_MOVIE = "ID_MOVIE"
    }
}
