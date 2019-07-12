package com.aranirahan.moviecatalogue.ui.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.RecyclerViewItemCountAssertion
import com.aranirahan.moviecatalogue.testing.SingleFragmentActivity
import com.aranirahan.moviecatalogue.ui.tvshow.TvShowFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest{
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvShowFragment = TvShowFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getData() {
        onView(withId(R.id.rv_tv_show)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).check(RecyclerViewItemCountAssertion(17))
    }
}