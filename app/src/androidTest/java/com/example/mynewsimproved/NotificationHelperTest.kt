package com.example.mynewsimproved


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynewsimproved.ui.notification.helper.NotificationHelper
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

@RunWith(AndroidJUnit4::class)
class NotificationHelperTest {

    private lateinit var helper: NotificationHelper

    @Before
    fun setUp() {
        helper =
            NotificationHelper(
                ApplicationProvider.getApplicationContext()
            )
    }

    @Test
    fun getQuery() {
        helper.query = "query"
        assertThat(helper.query, `is`("query"))
    }

    @Test
    fun getSections() {
        helper.sections = "sections"
        assertThat(helper.sections, `is`("sections"))
    }

    @Test
    fun getSwitchState() {
        helper.switchState = true
        assertThat(helper.switchState, `is`(true))
    }

    @Test
    fun getSearchParams() {
        helper.query = "query"
        helper.sections = "sections"
        helper.switchState = true
        val params = helper.getSearchParams()
        val todayString = SimpleDateFormat("yyyyMMdd", Locale.FRANCE).run {
            format(Date())
        }

        assertThat(params.sections, `is`(helper.sections))
        assertThat(helper.query, `is`(helper.query))
        assertThat(params.startDate, `is`(todayString))

    }

}
