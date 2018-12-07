package com.example.renaldysabdojatip.dicodingclub.ui.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.ui.Fragment.home.HomeFragment
import com.example.renaldysabdojatip.dicodingclub.ui.Fragment.ListTeamFragment
import com.example.renaldysabdojatip.dicodingclub.ui.Fragment.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadHomeFragment(savedInstanceState)

        bottom_navigation.menu.getItem(0).setCheckable(false)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    item.setCheckable(true)
                    loadHomeFragment(savedInstanceState)
                }
                R.id.menu_teams -> {
                    item.setCheckable(true)
                    loadTeamFragmnet(savedInstanceState)
                }
                R.id.menu_favorites -> {
                    item.setCheckable(true)
                    loadFavoriteFragment(savedInstanceState)
                }

            }
            true
        }

    }

    private fun loadHomeFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame, HomeFragment(), HomeFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamFragmnet(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame, ListTeamFragment(), ListTeamFragment::class.java.simpleName)
                    .commit()
        }
    }

}
