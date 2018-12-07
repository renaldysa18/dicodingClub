package com.example.renaldysabdojatip.dicodingclub.ui.Fragment.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.renaldysabdojatip.dicodingclub.Model.db.FavoriteTeam
import com.example.renaldysabdojatip.dicodingclub.Model.db.database

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.FavoriteTeamAdapter
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritesTeamFragment : Fragment() {

    lateinit var adapter: FavoriteTeamAdapter
    private var favorites: MutableList<FavoriteTeam> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_favorites, container, false)

        v.recyclerListFavorite.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        adapter = FavoriteTeamAdapter(favorites, requireContext())
        v.recyclerListFavorite.adapter = adapter
        return v
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
