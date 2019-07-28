package com.aranirahan.moviecatalogue.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.utils.RecyclerViewItemCountAssertion
import com.aranirahan.moviecatalogue.testing.SingleFragmentActivity
import com.aranirahan.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FavoriteMovieFragmentTest {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val favoriteMovieFragment = FavoriteMovieFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(favoriteMovieFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun getData() {
        onView(withId(R.id.rv_favorite_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_favorite_movie)).check(
            RecyclerViewItemCountAssertion(1)
        )
    }
}