package com.example.mynewsimproved.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import com.example.mynewsimproved.R
import kotlinx.android.synthetic.main.section_checkboxes.view.*

class SectionCheckboxes(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val checkboxes: ArrayList<CheckBox> = ArrayList()

    var sectionsText: String
        get() {
            var sections = String()

            checkboxes.forEach {
                if (it.isChecked) {
                    val section = it.text.toString()
                    sections += " $section"
                }

            }
            return sections
        }
        set(value) {
            checkboxes.forEach {
                if(value.contains(it.text.toString(), true)){
                    it.isChecked = true
                }
            }

        }


    init {

        LayoutInflater.from(context).inflate(R.layout.section_checkboxes, this, true)
        setPadding(
            resources.getDimensionPixelSize(R.dimen.space_20),
            resources.getDimensionPixelSize(R.dimen.space_20),
            resources.getDimensionPixelSize(R.dimen.space_20),
            resources.getDimensionPixelSize(R.dimen.space_20)
        )
        checkboxes.add(checkboxArts)
        checkboxes.add(checkboxBusiness)
        checkboxes.add(checkboxEntrepreneurs)
        checkboxes.add(checkboxPolitics)
        checkboxes.add(checkboxSports)
        checkboxes.add(checkboxTravel)
    }


}
