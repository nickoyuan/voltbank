package com.testapp.nick.voltbank

import androidx.test.espresso.Espresso.onData
import org.junit.Rule
import org.junit.Test
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.containsString

@LargeTest
class FindMatchActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MapsActivity::class.java)

    lateinit var mockServer : MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun checkIfSearchViewIsShown() {
        mockServer.setDispatcher(MockServerDispatcher().RequestDispatcher())
        onView(withId(R.id.floating_search_view)).check(matches(isDisplayed()))
    }

    @Test
    fun tappingOnSearchBoxShouldShowCalenderPicker() {
            onView(withId(R.id.search_bar_text))
                .perform(click())
        onView(withText("Select Crime Year and Month"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun tappingOnSearchBoxAndCancelCalenderPicker() {
        onView(withId(R.id.search_bar_text))
            .perform(click())

        onView(withId(R.id.cancel_action))
            .perform(click())
    }

    @Test
    fun tappingOnSearchBoxAndPickingYearAndMonth() {
        onView(withId(R.id.search_bar_text))
            .perform(click())
        onData(anything()).inAdapterView(withId(R.id.listview)).atPosition(0).perform(click())
        onView(withId(R.id.ok_action)).perform(click())
    }

    @Test
    fun pickingYearAndMonthWillMatchTheSearch() {
        onView(withId(R.id.search_bar_text))
            .perform(click())
        onData(anything()).inAdapterView(withId(R.id.listview)).atPosition(0).perform(click())
        onView(withId(R.id.ok_action)).perform(click())

        onView(withId(R.id.search_bar_text))
            .check(matches(withText(containsString("2017-5"))))
    }

}