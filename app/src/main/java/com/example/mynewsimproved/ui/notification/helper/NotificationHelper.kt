package com.example.mynewsimproved.ui.notification.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.mainactivity.MainActivity
import com.example.mynewsimproved.ui.searchResult.model.SearchParam
import java.text.SimpleDateFormat
import java.util.*


class NotificationHelper(val context: Context) {

    private val sharedPrefSearchPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES__KEY, Context.MODE_PRIVATE)

    var query: String
        get() {
            return sharedPrefSearchPreferences.getString(QUERY_KEY, "") ?: ""
        }
        set(value) {
            sharedPrefSearchPreferences.edit().putString(QUERY_KEY, value).apply()
        }

    var sections: String
        get() {
            return sharedPrefSearchPreferences.getString(SECTIONS_KEY, "") ?: ""

        }
        set(value) {
            sharedPrefSearchPreferences.edit().putString(SECTIONS_KEY, value).apply()
        }

    var switchState: Boolean
        get() {
            return sharedPrefSearchPreferences.getBoolean(SWITCH_STATE, false)

        }
        set(value) {
            sharedPrefSearchPreferences.edit().putBoolean(SWITCH_STATE, value).apply()
        }


    fun createNotification() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(PRIMARY_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val mainActivityIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(MainActivity.EXTRA_IS_NOTIFICATION, true)
        }

        val pendingMainActivityIntent = PendingIntent.getActivity(context, 0, mainActivityIntent, 0)


        val builder = NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
            .setSmallIcon(R.drawable.search)
            .setContentTitle(context.getString(R.string.notification_new_title))
            .setContentText(context.getString(R.string.notificaiton_new_subtitle))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingMainActivityIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(NEW_ARTICLES_NOTIFICATION_ID, builder.build())

        }
    }

    fun getSearchParams(): SearchParam {
        val startDate = SimpleDateFormat("yyyyMMdd", Locale.FRANCE).run {
            format(Date())
        }
        return SearchParam(query, startDate, null, sections)
    }

    companion object {
        private const val SHARED_PREFERENCES__KEY =
            "com.example.mynewsimproved.ui.notification.helper.sharedPreferencesKey"

        private const val QUERY_KEY =
            "com.example.mynewsimproved.ui.notification.helper.queryKey"

        private const val SECTIONS_KEY =
            "com.example.mynewsimproved.ui.notification.helper.sectionsKey"

        private const val SWITCH_STATE =
            "com.example.mynewsimproved.ui.notification.helper.switchState"

        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"

        private const val NEW_ARTICLES_NOTIFICATION_ID = 1

    }
}
