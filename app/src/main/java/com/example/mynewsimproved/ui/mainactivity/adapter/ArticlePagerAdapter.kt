package com.example.mynewsimproved.ui.mainactivity.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.article.ArticleFragment


class ArticlePagerAdapter(
    fm: FragmentManager,
    private val numberTabs: Int,
    private val context: Context
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAB_TITLES = arrayOf(
        R.string.tab_label1,
        R.string.tab_label2,
        R.string.tab_label3
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ArticleFragment.newInstance()
            1 -> ArticleFragment.newInstance("home")
            2 -> ArticleFragment.newInstance("technology")
            else -> throw (Throwable("Unsupported position"))

        }
    }

    override fun getCount(): Int {
        return numberTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}
