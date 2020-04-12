package com.example.mynewsimproved.ui.notification

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.utils.createOrCancelAlarm
import kotlinx.android.synthetic.main.fragment_search.*

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //dateLayout.visibility = View.GONE
        searchButton.visibility = View.GONE


        var sections = String()
        val checkboxes: ArrayList<CheckBox> = ArrayList()
//        checkboxes.add(checkboxArts)
//        checkboxes.add(checkboxBusiness)
//        checkboxes.add(checkboxEntrepreneurs)
//        checkboxes.add(checkboxPolitics)
//        checkboxes.add(checkboxSports)
//        checkboxes.add(checkboxTravel)


        val sharedPrefSearchPreferences: SharedPreferences = requireContext().getSharedPreferences("search_params", Context.MODE_PRIVATE)
        val stringList = sharedPrefSearchPreferences.getString("sections", null)?.split("\\s".toRegex())

        for (item in checkboxes) {
            if (stringList != null) {
                item.setOnClickListener{
//                    switchWidget.isChecked = false
                }
                for(string in stringList)
                    if(string == item.text.toString()) item.isChecked = true
            }
        }
        val queryString = sharedPrefSearchPreferences.getString("query", null).toString()

        if(queryString != "null") queryEditText.setText(queryString)


//        if(sharedPrefSearchPreferences.getBoolean("isChecked", false)) switchWidget.isChecked = true
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
//                switchWidget.isChecked = false
            }
        })


//        switchWidget.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//
//
//                for (item in checkboxes) {
//                    if (item.isChecked) {
//                        val section = item.text.toString()
//                        sections = "$sections $section"
//                    }
//
//                }
//
//                if (sections == "") {
//                    Toast.makeText(requireContext(), "You should pick at least one section", Toast.LENGTH_LONG).show()
//                    switchWidget.isChecked = false
//                } else {
//                    val query = queryEditText.text.toString()
//
//                    sharedPrefSearchPreferences.edit().putString("query", query).apply()
//                    sharedPrefSearchPreferences.edit().putString("sections", sections).apply()
//                    sharedPrefSearchPreferences.edit().putBoolean("isChecked", true).apply()
//                    createOrCancelAlarm(requireContext(),true)
//                    Toast.makeText(requireContext(), "You will be reminded tomorrow at 7.00", Toast.LENGTH_LONG).show()
//
//                }
//            }else{
//                createOrCancelAlarm(requireContext(), false)
//                Toast.makeText(requireContext(), "Notifications disabled", Toast.LENGTH_LONG).show()
//
//            }
//
//        }

    }
}
