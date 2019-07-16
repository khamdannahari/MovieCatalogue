package com.aranirahan.moviecatalogue.ui.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.RecyclerViewItemCountAssertion
import com.aranirahan.moviecatalogue.testing.SingleFragmentActivity
import com.aranirahan.moviecatalogue.utils.EspressoIdlingResource
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
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun getData() {
        onView(withId(R.id.rv_tv_show)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).check(RecyclerViewItemCountAssertion(16))
    }
}