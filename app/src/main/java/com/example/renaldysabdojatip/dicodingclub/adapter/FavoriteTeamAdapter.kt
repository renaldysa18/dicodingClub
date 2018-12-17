package com.example.renaldysabdojatip.dicodingclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.renaldysabdojatip.dicodingclub.model.db.FavoriteTeam
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.ui.activity.DetailTeam
import kotlinx.android.synthetic.main.list_favorite.view.*

class FavoriteTeamAdapter(val items : List<FavoriteTeam>, val context : Context) : RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_favorite, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val list = items.get(p1)

        Glide.with(context)
                .load(list.teamBadge)
                .into(p0?.imgvFavorite)
        Log.d("DataTeam", list.toString())
        p0?.tvNameTeam?.text = list.teamName

        val idTeam : String = list.teamId.toString()

        p0?.card?.setOnClickListener(View.OnClickListener { v->
            toDetailTeam(context, idTeam)
        })
    }

    private fun toDetailTeam(context: Context, idTeam: String) {
        val intent = Intent(context, DetailTeam::class.java)
        intent.putExtra("idTeam", idTeam)
        context?.startActivity(intent)
    }


    class ViewHolder(view : View) :RecyclerView.ViewHolder(view){
        val imgvFavorite = view.imgvListFavorite
        val tvNameTeam = view.tvnameTeamListFavorite
        val card = view.cardListFavorite
    }
}