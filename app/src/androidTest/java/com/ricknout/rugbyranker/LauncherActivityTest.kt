package com.ricknout.rugbyranker

import androidx.test.espresso.Espresso.onView
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ricknout.rugbyranker.EspressoUtils.childAtPosition

import com.ricknout.rugbyranker.R

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
@LargeTest
@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun launcherActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // Idling resources are officially recommended but difficult to get working so I'm not using that right now.
        Thread.sleep(7000)

        val imageButton = onView(
            allOf(
                withId(R.id.addMatchPredictionFab),
                childAtPosition(
                    allOf(
                        withId(R.id.coordinatorLayout),
                        childAtPosition(
                            withId(R.id.navHostFragment),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val frameLayout = onView(
            allOf(
                withId(R.id.mensFragment),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNavigationView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))
    }

}
