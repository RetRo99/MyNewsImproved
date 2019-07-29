package com.example.mynewsoc.Utils

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.mynewsimproved.BroadcastReceiver.AlarmReceiver
import com.example.mynewsimproved.R
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun createOrCancelAlarm(context: Context, set: Boolean) {

    val intentAction = "SHOW_NEW_ARTICLES"


    //Setting up alarm every day on midnight


    //Creating new intent object
   val intent = Intent(context, AlarmReceiver::class.java)

    //Adding action to the intent
    intent.action = intentAction

    //Creating PendingIntent from previously created intent
    val pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


    //Getting the calendar instance and setting it to next midnight
    val setCalendar = Calendar.getInstance()
    setCalendar.set(Calendar.HOUR_OF_DAY, 7)
    setCalendar.set(Calendar.MINUTE, 0)
    setCalendar.set(Calendar.SECOND, 0)
    setCalendar.add(Calendar.DATE, 1)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    if(set) {
        //Setting the repeating alarmmanager with real time  (RTC), passing the calendar instance as the triggerat parameter, and running it everyday

        alarmManager.setRepeating(
            AlarmManager.RTC,
            setCalendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pi
        )
    }else{
        alarmManager.cancel(pi)
    }
}



fun createNotification(numberOfNew:Int, context: Context) {
    val PRIMARY_CHANNEL_ID = "primary_notification_channel"

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


    val builder = NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
        .setSmallIcon(R.drawable.search)
        .setContentTitle("there are new articles")
        .setContentText("$numberOfNew new articles")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notify(1, builder.build())

    }
}

