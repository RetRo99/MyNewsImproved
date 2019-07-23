package com.example.mynewsimproved.Pager

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mynewsimproved.PagerFragments.MostViewedStoriesFragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.PagerFragments.TechnologyFragment
import com.example.mynewsimproved.PagerFragments.TopStoriesFragment

class PagerAdapter(fm: FragmentManager, private val numberofTabs:Int, private val context: Context):FragmentStatePagerAdapter(fm) {

    private val TAB_TITLES = arrayOf(
        R.string.tab_label1,
        R.string.tab_label2,
        R.string.tab_label3
    )


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return TopStoriesFragment()
            1 -> return MostViewedStoriesFragment()
            2 -> return TechnologyFragment()

        }
        Log.d("position", position.toString())
        return TechnologyFragment()
    }


    override fun getCount(): Int {
        return numberofTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}