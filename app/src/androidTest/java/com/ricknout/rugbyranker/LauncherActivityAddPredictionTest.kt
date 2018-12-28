package com.ricknout.rugbyranker

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ricknout.rugbyranker.EspressoUtils.childAtPosition
import com.ricknout.rugbyranker.EspressoUtils.isTextLength
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LauncherActivityAddPredictionTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun launcherActivityAddTest() {
        // Added a sleep statement to match the app's execution delay.
        // Idling resources are officially recommended but difficult to get working so I'm not using that right now.
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val floatingActionButton = onView(
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
        floatingActionButton.perform(click())

        val clearButton = onView(withId(R.id.clearOrCancelButton))
        clearButton.check(matches(isDisplayed()))

        val closeButton = onView(withId(R.id.closeButton))
        closeButton.check(matches(isDisplayed()))

        val addPredictionButton = onView(withId(R.id.addOrEditMatchPredictionButton))
        addPredictionButton.check(matches(isDisplayed()))
        addPredictionButton.check(matches(not(isEnabled())))

        val homeTeamEditText = onView(allOf(withId(R.id.homeTeamEditText), isDisplayed()))
        homeTeamEditText.perform(click())

        Thread.sleep(250)

        val homeTeamTextView = onView(
            allOf(
                withId(R.id.title),
                withText("\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F England"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        homeTeamTextView.perform(click())

        Thread.sleep(250)

        val awayTeamEditText = onView(allOf(withId(R.id.awayTeamEditText), isDisplayed()))
        awayTeamEditText.perform(click())

        Thread.sleep(250)

        val awayTeamTextView = onView(
            allOf(
                withId(R.id.title), withText("\uD83C\uDDFF\uD83C\uDDE6 South Africa"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        awayTeamTextView.perform(click())

        val homePointsEditText = onView(
            allOf(
                withId(R.id.homePointsEditText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.homePointsTextInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        homePointsEditText.perform(typeText("50"), closeSoftKeyboard())

        val awayPointsEditText = onView(
            allOf(
                withId(R.id.awayPointsEditText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.awayPointsTextInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        awayPointsEditText.perform(typeText("11"), closeSoftKeyboard())

        addPredictionButton.check(matches(isDisplayed()))
        addPredictionButton.check(matches(isEnabled()))
        addPredictionButton.perform(click())

        val chip = onView(
            allOf(
                withId(R.id.chip),
                isTextLength(6, 50),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.matchPredictionsRecyclerView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        chip.perform(click())
        chip.check(doesNotExist())
    }
}
