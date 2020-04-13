package com.example.mynewsimproved.ui.notification.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mynewsimproved.ui.notification.helper.NotificationHelper

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val helper = NotificationHelper(applicationContext)
        helper.createNotification()
        return Result.success()
    }
}
