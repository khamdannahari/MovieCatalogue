package com.aranirahan.moviecatalogue.ui.detailmovie


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.utils.goGone
import com.aranirahan.moviecatalogue.utils.goVisible
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.aranirahan.moviecatalogue.vo.Resource
import com.aranirahan.moviecatalogue.vo.Status.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private val vmDetailMovie by lazy { obtainViewModel(this) }

    private val idMovie by lazy { intent.getIntExtra(ID_MOVIE, 0) }

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        vmDetailMovie.detailMovie(idMovie).observe(this, Observer {
            initView(it)

            vmDetailMovie.setIdMovie(idMovie)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu
        vmDetailMovie.favoriteMovie.observe(this, Observer<Resource<Movie>> { response ->
            response?.let {
                when (response.status) {
                    LOADING -> progress_circular.goVisible()
                    SUCCESS -> {
                        progress_circular.goGone()

                        response.data?.let {
                            val state = it.isFavorite
                            setIsFavoriteState(state)
                        }
                    }
                    ERROR -> {
                        progress_circular.goGone()
                        Toast.makeText(applicationContext, getString(R.string.something_wrong), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        })
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            vmDetailMovie.setIsFavoriteMovie()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setIsFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_selected)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_unselected)
        }
    }

    companion object {
        const val ID_MOVIE = "ID_MOVIE"
    }

}
