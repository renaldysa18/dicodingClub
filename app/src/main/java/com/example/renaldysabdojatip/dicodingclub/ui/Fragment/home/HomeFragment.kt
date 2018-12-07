package com.example.renaldysabdojatip.dicodingclub.ui.Fragment.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.tabs.TabAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        val tabAdapter = TabAdapter(childFragmentManager)
        v.viewPagerMain.adapter = tabAdapter
        v.tabsMain.setupWithViewPager(v.viewPagerMain)

        return v
    }


}
