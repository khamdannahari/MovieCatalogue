package com.aranirahan.moviecatalogue.ui.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.utils.RecyclerViewItemCountAssertion
import com.aranirahan.moviecatalogue.testing.SingleFragmentActivity
import com.aranirahan.moviecatalogue.utils.EspressoIdlingResource2
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FavoriteTvShowFragmentTest {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val favoriteTvShowFragment = FavoriteTvShowFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource2.getEspressoIdlingResource())
        activityRule.activity.setFragment(favoriteTvShowFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource2.getEspressoIdlingResource())
    }

    @Test
    fun getData() {
        onView(withId(R.id.rv_favorite_tv_show)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_show)).check(RecyclerViewItemCountAssertion(1))
    }
}