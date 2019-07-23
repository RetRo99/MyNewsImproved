package com.example.mynewsimproved

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : AppCompatActivity() {
    lateinit var startCalendar: Calendar
    lateinit var endCalendar: Calendar
    lateinit var model: SearchViewModel
    var startDateConvertedString: String? = null
    var endDateConvertedString: String? = null

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

        switchWidget.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) Toast.makeText(this, "Feature not yet implemented", Toast.LENGTH_SHORT).show()

        }

    }

    private fun setSearchScreen() {
        startCalendar = Calendar.getInstance()
        endCalendar = Calendar.getInstance()
        switchWidget.visibility = View.GONE
        model = ViewModelProviders.of(this).get(SearchViewModel::class.java)

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
            model.loadSearchedArticles(query, startDateConvertedString, endDateConvertedString, sections)
            val searchResultIntent = Intent(this, SearchResult::class.java)
            searchResultIntent.putExtra("query", query)
            searchResultIntent.putExtra("startDateConvertedString", startDateConvertedString)
            searchResultIntent.putExtra("endDateConvertedString", endDateConvertedString)
            searchResultIntent.putExtra("sections", sections)


            startActivity(searchResultIntent)

        }




        Log.d("če", "bi bla bela")


    }

}
