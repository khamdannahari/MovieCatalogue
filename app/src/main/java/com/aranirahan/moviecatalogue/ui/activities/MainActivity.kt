package com.aranirahan.moviecatalogue.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.ui.fragments.MovieFragment
import com.aranirahan.moviecatalogue.ui.fragments.TvShowFragment
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

        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> MovieFragment()
                else -> TvShowFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> getString(R.string.movies)
                else -> getString(R.string.tv_show)
            }
        }
    }
}
