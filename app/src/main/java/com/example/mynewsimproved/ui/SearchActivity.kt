package com.example.mynewsimproved.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewsimproved.utils.createOrCancelAlarm
import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.text.Editable
import android.text.TextWatcher
import com.example.mynewsimproved.R


class SearchActivity : AppCompatActivity() {
    private lateinit var startCalendar: Calendar
    private lateinit var endCalendar: Calendar
    private var startDateConvertedString: String? = null
    private var endDateConvertedString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val mToolbar = findViewById<androidx.appcompat.widget.Toolbar?>(R.id.activity_toolbar)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (!intent.getBooleanExtra("Notification", true)) setSearchScreen()
        else {
            setNotificationScreen()
        }
    }

    private fun setNotificationScreen() {
        val layout: LinearLayout =
            findViewById(R.id.fragment_base_search_notification_date_management_linear_layout)
        layout.visibility = View.GONE
        searchButton.visibility = View.GONE


        var sections = String()
        val checkboxes: ArrayList<CheckBox> = ArrayList()
        checkboxes.add(checkboxArts)
        checkboxes.add(checkboxBusiness)
        checkboxes.add(checkboxEntrepreneurs)
        checkboxes.add(checkboxPolitics)
        checkboxes.add(checkboxSports)
        checkboxes.add(checkboxTravel)


        val sharedPrefSearchPreferences: SharedPreferences = getSharedPreferences("search_params", Context.MODE_PRIVATE)
        val stringList = sharedPrefSearchPreferences.getString("sections", null)?.split("\\s".toRegex())

            for (item in checkboxes) {
                if (stringList != null) {
                    item.setOnClickListener{
                        switchWidget.isChecked = false
                    }
                    for(string in stringList)
                        if(string == item.text.toString()) item.isChecked = true
                }
            }
        val queryString = sharedPrefSearchPreferences.getString("query", null).toString()

        if(queryString != "null") queryEditText.setText(queryString)


        if(sharedPrefSearchPreferences.getBoolean("isChecked", false)) switchWidget.isChecked = true
        queryEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                switchWidget.isChecked = false
            }
        })


        switchWidget.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {


                for (item in checkboxes) {
                    if (item.isChecked) {
                        val section = item.text.toString()
                        sections = "$sections $section"
                    }

                }

            if (sections == "") {
                    Toast.makeText(this, "You should pick at least one section", Toast.LENGTH_LONG).show()
                    switchWidget.isChecked = false
                } else {
                val query = queryEditText.text.toString()

                    sharedPrefSearchPreferences.edit().putString("query", query).apply()
                    sharedPrefSearchPreferences.edit().putString("sections", sections).apply()
                    sharedPrefSearchPreferences.edit().putBoolean("isChecked", true).apply()
                    createOrCancelAlarm(this,true)
                    Toast.makeText(this, "You will be reminded tomorrow at 7.00", Toast.LENGTH_LONG).show()

            }
            }else{
                createOrCancelAlarm(this, false)
                Toast.makeText(this, "Notifications disabled", Toast.LENGTH_LONG).show()

            }

        }

    }

    private fun setSearchScreen() {
        startCalendar = Calendar.getInstance()
        endCalendar = Calendar.getInstance()
        switchWidget.visibility = View.GONE

        val startDate = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            startCalendar.set(Calendar.YEAR, year)
            startCalendar.set(Calendar.MONTH, monthOfYear)
            startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateStartEditText()
        }
        val endDate = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            endCalendar.set(Calendar.YEAR, year)
            endCalendar.set(Calendar.MONTH, monthOfYear)
            endCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateEndEditText()
        }

        startEditText.setOnClickListener {
            DatePickerDialog(
                this, startDate, startCalendar
                    .get(Calendar.YEAR), startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        endEditText.setOnClickListener {
            DatePickerDialog(
                this, endDate, startCalendar
                    .get(Calendar.YEAR), startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        searchButton.setOnClickListener {
            startActivity()
        }
    }

    private fun updateStartEditText() {
        val myFormat = "MM-dd-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)

        startEditText.setText(sdf.format(startCalendar.time))
        startDateConvertedString = SimpleDateFormat("yyyyMMdd", Locale.FRANCE).format(startCalendar.time)
    }

    private fun updateEndEditText() {
        val myFormat = "MM-dd-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
        endEditText.setText(sdf.format(endCalendar.time))
        endDateConvertedString = SimpleDateFormat("yyyyMMdd", Locale.FRANCE).format(endCalendar.time)

    }

    private fun startActivity() {

        val query = queryEditText.text.toString()

        var sections = String()
        val checkboxes: ArrayList<CheckBox> = ArrayList()
        checkboxes.add(checkboxArts)
        checkboxes.add(checkboxBusiness)
        checkboxes.add(checkboxEntrepreneurs)
        checkboxes.add(checkboxPolitics)
        checkboxes.add(checkboxSports)
        checkboxes.add(checkboxTravel)

        for (item in checkboxes) {
            if (item.isChecked) {
                val section = item.text.toString()
                sections = "$sections $section"
            }

        }

        if (sections == "" && query == "") {
            Toast.makeText(
                this,
                "You should enter at least one parameter and pick at least one section",
                Toast.LENGTH_LONG
            ).show()
        } else if (sections == "") {
            Toast.makeText(this, "You should pick at least one section", Toast.LENGTH_LONG).show()
        } else if (query == "") {
            Toast.makeText(this, "You should enter at least one parameter", Toast.LENGTH_LONG).show()

        } else {
            val searchResultIntent = Intent(this, SearchResult::class.java)
            searchResultIntent.putExtra("query", query)
            searchResultIntent.putExtra("startDateConvertedString", startDateConvertedString)
            searchResultIntent.putExtra("endDateConvertedString", endDateConvertedString)
            searchResultIntent.putExtra("sections", sections)

            startActivity(searchResultIntent)

        }
    }
}
