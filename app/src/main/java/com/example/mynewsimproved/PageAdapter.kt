package com.example.mynewsimproved

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PageAdapter(fm: FragmentManager, private val numberofTabs:Int, private val context: Context):FragmentStatePagerAdapter(fm) {

    private val TAB_TITLES = arrayOf(
        R.string.tab_label1,
        R.string.tab_label2,
        R.string.tab_label3
    )


    override fun getItem(position: Int): Fragment {
        Log.d("hey", "there")
        return TechnologyFragment()
    }


    override fun getCount(): Int {
        return numberofTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}