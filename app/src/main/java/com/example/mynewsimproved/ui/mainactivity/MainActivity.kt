package com.example.mynewsimproved.ui.mainactivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mynewsimproved.R
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

        if(intent.getBooleanExtra(EXTRA_IS_NOTIFICATION, false)){
            fromSearchToSearchResult()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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
            R.id.action_notification -> toNotificationScreen()
            R.id.searchButton -> toSearchFragment()
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
                toNotificationScreen()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun fromSearchToSearchResult(searchParam: SearchParam) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SearchResult.newInstance(searchParam)).addToBackStack(null)
            .commit()
    }

     fun fromSearchToSearchResult() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SearchResult.newInstance()).addToBackStack(null)
            .commit()
    }

    private fun toNotificationScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, NotificationFragment.newInstance())
            .addToBackStack(null).commit()
    }

    private fun toSearchFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SearchFragment.newInstance())
            .addToBackStack(null).commit()
    }

    override fun fromHomeToWeb(url: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, WebViewFragment.newInstance(url)).addToBackStack(null)
            .commit()

    }

    companion object {
        const val EXTRA_IS_NOTIFICATION =
            "com.example.mynewsimproved.ui.mainactivity.extraIsNotificiation"
    }
}
