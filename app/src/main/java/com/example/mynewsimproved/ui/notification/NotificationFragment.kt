package com.example.mynewsimproved.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.notification.helper.NotificationHelper
import com.example.mynewsimproved.ui.notification.worker.NotificationWorker
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_notification.section_checkboxes
import kotlinx.android.synthetic.main.fragment_search.queryEditText
import java.util.concurrent.TimeUnit

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val helper = NotificationHelper(requireContext())


        queryEditText.setText(helper.query)


        switchWidget.isChecked = helper.switchState

        queryEditText.doOnTextChanged { _, _, _, _ ->
            switchWidget.isChecked = false

        }
        section_checkboxes.sectionsText = helper.sections
        switchWidget.isChecked = helper.switchState


        switchWidget.setOnCheckedChangeListener { _, isChecked ->

            val msg = when {
                isChecked && section_checkboxes.sectionsText.isEmpty() -> {
                    switchWidget.isChecked = false
                    R.string.toast_one_section
                }

                !isChecked -> {
                    helper.query = ""
                    helper.sections = ""
                    helper.switchState = false
                    R.string.notificaitons_disabled
                }

                else -> {
                    helper.query = queryEditText.text.toString()
                    helper.sections = section_checkboxes.sectionsText
                    helper.switchState = true

                    setupNotificationWorker()
                    R.string.notificaiton_time
                }
            }

            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()


        }

    }


    private fun setupNotificationWorker() {
        val work = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
            .build()

        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueueUniquePeriodicWork(
            "midnight_worker",
            ExistingPeriodicWorkPolicy.KEEP, work
        )

    }

    companion object {
        fun newInstance() = NotificationFragment()
    }
}
