package com.example.renaldysabdojatip.dicodingclub.ui.fragment.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.renaldysabdojatip.dicodingclub.model.db.FavoriteMatch

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.FavoriteMatchAdapter
import kotlinx.android.synthetic.main.fragment_favorites_match.view.*
import com.example.renaldysabdojatip.dicodingclub.model.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritesMatchFragment : Fragment() {

    private lateinit var adapter: FavoriteMatchAdapter
    private var favorites: MutableList<FavoriteMatch> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_favorites_match, container, false)

        v.recyclerListFavoriteMatch.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        adapter = FavoriteMatchAdapter(favorites, requireContext())
        v.recyclerListFavoriteMatch.adapter = adapter

        return v
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteMatch.FAVORITE_TABLE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
