package com.example.mynewsimproved.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.home.adapter.ArticlePagerAdapter
import com.example.mynewsimproved.ui.mainactivity.MainView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment :Fragment(){

    private lateinit var mainView: MainView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainView){
            mainView = context
        }else {
            throw Exception("must implement mainview interface")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pager.adapter =
            ArticlePagerAdapter(
                childFragmentManager,
                3,
                requireContext()
            )
        pager.offscreenPageLimit = 3
    }

}
