package com.aranirahan.moviecatalogue.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.ui.movie.FavoriteMovieFragment
import com.aranirahan.moviecatalogue.ui.movie.MovieFragment
import com.aranirahan.moviecatalogue.ui.tvshow.FavoriteTvShowFragment
import com.aranirahan.moviecatalogue.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTabAdapterAndViewPager()
    }

    private fun setupTabAdapterAndViewPager() {
        view_pager.adapter = HomePagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
    }

    inner class HomePagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        override fun getCount(): Int = 4

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> MovieFragment()
                1 -> TvShowFragment()
                2 -> FavoriteMovieFragment()
                else -> FavoriteTvShowFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> getString(R.string.movies)
                1 -> getString(R.string.tv_show)
                2 -> getString(R.string.favorite_movies)
                else -> getString(R.string.favorite_tv_show)
            }
        }
    }
}
