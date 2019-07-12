package com.aranirahan.moviecatalogue.ui.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.RecyclerViewItemCountAssertion
import com.aranirahan.moviecatalogue.testing.SingleFragmentActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MovieFragmentTest {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val moviesFragment = MovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(moviesFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getData() {
        onView(withId(R.id.rv_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(16))
    }
}