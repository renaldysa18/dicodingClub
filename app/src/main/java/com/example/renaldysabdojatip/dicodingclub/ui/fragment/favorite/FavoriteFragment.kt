package com.example.renaldysabdojatip.dicodingclub.ui.fragment.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.tabs.TabFavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_favorite, container, false)

        val tabsApapter = TabFavoriteAdapter(childFragmentManager)
        v.viewPagerFavorite.adapter = tabsApapter
        v.tabsFavorite.setupWithViewPager(v.viewPagerFavorite)

        return v
    }


}
