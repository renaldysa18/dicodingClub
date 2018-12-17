package com.example.renaldysabdojatip.dicodingclub

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.renaldysabdojatip.dicodingclub.ui.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.renaldysabdojatip.dicodingclub.R.id.*

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour(){
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(recyclerLastMatch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(recyclerLastMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(recyclerLastMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.pressBack()
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(menu_favorites)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withText("MATCH"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withText("MATCH")).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(recyclerListFavoriteMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(recyclerListFavoriteMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.pressBack()
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(menu_home)).perform(ViewActions.click())
        Thread.sleep(3000)
    }
}