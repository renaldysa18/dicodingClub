package com.example.renaldysabdojatip.dicodingclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.renaldysabdojatip.dicodingclub.Model.TeamObject
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.ui.Activity.DetailTeam
import kotlinx.android.synthetic.main.list_team_favorite.view.*

class ListTeamAdapter(val items : List<TeamObject>, val context: Context) : RecyclerView.Adapter<ListTeamAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.list_team_favorite, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val list = items.get(p1)

        p0.tvNameTeam?.text = list?.strTeam
        Glide.with(context)
                .load(list?.strTeamBadge)
                .into(p0.imgTeam)
        val idTeam : String = list?.idTeam.toString()

        p0.cardTeam?.setOnClickListener(View.OnClickListener { v->
            toDetailTeam(context, idTeam)
        })

    }

    private fun toDetailTeam(context: Context, idTeam: String?) {
        val intent = Intent(context, DetailTeam::class.java)
        intent.putExtra("idTeam", idTeam)
        context?.startActivity(intent)
    }

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val cardTeam = view.cardTeam
        val imgTeam = view.imgvTeam
        val tvNameTeam = view.tvNameTeam
    }
}