package com.example.mynewsimproved.ui.search

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.mainactivity.MainView
import com.example.mynewsimproved.ui.searchResult.model.SearchParam
import com.example.mynewsimproved.utils.SectionCheckboxes
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.section_checkboxes.*
import java.text.SimpleDateFormat
import java.util.*


class SearchFragment : Fragment() {
    private var startDateConvertedString: String? = null
    private var endDateConvertedString: String? = null

    private lateinit var parentView: MainView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainView) {
            parentView = context
        } else {
            throw Exception("must implement mainview interface")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startEditText.setOnClickListener {
            showDatePickerDialog {
                updateStartEditText(it)
            }
        }

        endEditText.setOnClickListener {
            showDatePickerDialog {
                updateEndEditText(it)
            }
        }
        searchButton.setOnClickListener {
            onSearchButtonClicked()
        }
    }

    private fun showDatePickerDialog(onDateSetAction: (Calendar) -> Unit) {
        val calendar = Calendar.getInstance()

        val listener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, monthOfYear)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)

            }
            onDateSetAction(calendar)
        }

        DatePickerDialog(
            requireContext(), listener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

    private fun updateStartEditText(calendar: Calendar) {
        val myFormat = "MM-dd-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)

        startEditText.setText(sdf.format(calendar.time))
        startDateConvertedString =
            SimpleDateFormat("yyyyMMdd", Locale.FRANCE).format(calendar.time)
    }

    private fun updateEndEditText(calendar: Calendar) {
        val myFormat = "MM-dd-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
        endEditText.setText(sdf.format(calendar.time))
        endDateConvertedString = SimpleDateFormat("yyyyMMdd", Locale.FRANCE).format(calendar.time)

    }

    private fun onSearchButtonClicked() {

        val query = queryEditText.text.toString()

        val sections = (section_checkboxes as SectionCheckboxes).sectionsText

         when {
            sections.isEmpty() && query.isEmpty() -> showToast(R.string.toast_one_section_and_parimeter)
            sections.isEmpty() -> showToast(R.string.toast_one_section)
            query.isEmpty() -> showToast(R.string.toast_one_parameter)
            else -> parentView.fromSearchToSearchResult(SearchParam(query, startDateConvertedString, endDateConvertedString, sections))
        }
    }

    private fun showToast(@StringRes msg: Int) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
