package com.example.renaldysabdojatip.dicodingclub.adapter.tabs

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.renaldysabdojatip.dicodingclub.ui.fragment.home.LastMatchFragment
import com.example.renaldysabdojatip.dicodingclub.ui.fragment.home.NextMatchFragment

class TabAdapter (fm : FragmentManager ): FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return when (p0){
            0 -> {
                LastMatchFragment()
            }
            else -> {
                NextMatchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Last Match"
            else -> "Next Match"
        }
    }
}