package com.example.mynewsimproved.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mynewsimproved.ui.mainactivity.adapter.ArticlePagerAdapter
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.SearchActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        pager.adapter =
            ArticlePagerAdapter(
                supportFragmentManager,
                3,
                this
            )
        pager.offscreenPageLimit = 3

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

        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_notification -> {
                val searchIntent = Intent(this, SearchActivity::class.java)
                startActivity(searchIntent)
                true
            }
            R.id.searchButton -> {
                val searchIntent = Intent(this, SearchActivity::class.java)
                searchIntent.putExtra("Notification", false)
                startActivity(searchIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val tabs = findViewById<View>(R.id.tab_layout) as TabLayout
        when (item.itemId) {
            R.id.nav_technology -> {
                tabs.getTabAt(2)?.select()
            }
            R.id.nav_mostviewed -> {
                tabs.getTabAt(1)?.select()


            }
            R.id.nav_topstories -> {
                tabs.getTabAt(0)?.select()


            }
            R.id.nav_notifications -> {
                val searchIntent = Intent(this, SearchActivity::class.java)
                startActivity(searchIntent)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
