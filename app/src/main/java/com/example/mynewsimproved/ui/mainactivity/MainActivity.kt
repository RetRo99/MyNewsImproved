package com.example.mynewsimproved.ui.mainactivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.home.HomeFragment
import com.example.mynewsimproved.ui.notification.NotificationFragment
import com.example.mynewsimproved.ui.search.SearchFragment
import com.example.mynewsimproved.ui.searchResult.SearchResult
import com.example.mynewsimproved.ui.searchResult.model.SearchParam
import com.example.mynewsimproved.ui.web.WebViewFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (intent.getBooleanExtra(EXTRA_IS_NOTIFICATION, false)) {
            fromSearchToSearchResult()
        }

        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.title = getString(R.string.title_home)


        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.findFragmentById(R.id.fragment_container) is HomeFragment) {
                toolbar.setNavigationIcon(R.drawable.ic_menu)
                toolbar.title = getString(R.string.title_home)

            }
        }

        toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.findFragmentById(R.id.fragment_container) is HomeFragment) {
                drawer_layout.openDrawer(GravityCompat.START)
            }
            else{
                supportFragmentManager.popBackStack()
            }
        }


    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> true // TODO to settings
            R.id.action_notification -> fromHomeToNotificationScreen()
            R.id.searchButton -> fromHomeToSearchFragment()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_technology -> {
                tab_layout?.getTabAt(2)?.select()
            }
            R.id.nav_mostviewed -> {
                tab_layout?.getTabAt(1)?.select()
            }
            R.id.nav_topstories -> {
                tab_layout?.getTabAt(0)?.select()
            }
            R.id.nav_notifications -> {
                fromHomeToNotificationScreen()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun fromSearchToSearchResult(searchParam: SearchParam) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SearchResult.newInstance(searchParam))
            .addToBackStack(null)
            .commit()
    }

    override fun setupToolbar(title: Int, icon: Int) {
        toolbar.setNavigationIcon(icon)
        toolbar.title = getString(title)

    }

    private fun fromSearchToSearchResult() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SearchResult.newInstance()).addToBackStack(null)
            .commit()
    }

    private fun fromHomeToNotificationScreen() {
        if (!isFragmentAlreadyAdded(NOTIFICATION_FRAGMENT_TAG)) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    NotificationFragment.newInstance(),
                    NOTIFICATION_FRAGMENT_TAG
                )
                .addToBackStack(null).commit()
        }
    }

    private fun fromHomeToSearchFragment() {
        if (!isFragmentAlreadyAdded(SEARCH_FRAGMENT_TAG)) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SearchFragment.newInstance(), SEARCH_FRAGMENT_TAG)
                .addToBackStack(null).commit()
        }
    }

    override fun fromHomeToWeb(url: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, WebViewFragment.newInstance(url)).addToBackStack(null)
            .commit()

    }

    private fun isFragmentAlreadyAdded(tag: String): Boolean {
        return supportFragmentManager.findFragmentByTag(tag)?.isAdded ?: false

    }

    companion object {
        const val EXTRA_IS_NOTIFICATION =
            "com.example.mynewsimproved.ui.mainactivity.extraIsNotificiation"

        private const val SEARCH_FRAGMENT_TAG =
            "com.example.mynewsimproved.ui.mainactivity.searchFragment"

        private const val NOTIFICATION_FRAGMENT_TAG =
            "com.example.mynewsimproved.ui.mainactivity.notificationFragment"
    }
}
