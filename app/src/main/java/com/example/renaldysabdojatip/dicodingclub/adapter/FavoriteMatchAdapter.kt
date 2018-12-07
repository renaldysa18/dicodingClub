package com.example.renaldysabdojatip.dicodingclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.renaldysabdojatip.dicodingclub.Model.db.FavoriteMatch
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.ui.Activity.DetailMatch
import kotlinx.android.synthetic.main.list_match_favorite.view.*
import org.jetbrains.anko.toast

class FavoriteMatchAdapter(val items : List<FavoriteMatch>,val context: Context) :RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_match_favorite, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val list = items.get(p1)

        p0?.date?.text = list.eventDate
        p0?.homeTeam?.text = list.homeTeam
        p0?.awayTeam?.text = list?.awayTeam
        p0?.homeScore?.text = list?.homeScore
        p0?.awayScore?.text = list?.awayScore

        val idHomeTeam : String = list?.homeTeamId.toString()
        val idAwayTeam : String = list?.awayTeamId.toString()
        val idEvent : String = list?.eventId.toString()

        p0?.card?.setOnClickListener(View.OnClickListener { v->
            toDetailMatch(context, idHomeTeam, idAwayTeam, idEvent)
        })
    }

    private fun toDetailMatch(context: Context, idHomeTeam: String, idAwayTeam: String, idEvent: String) {
        val intent = Intent(context, DetailMatch::class.java)
        intent.putExtra("idHomeTeam", idHomeTeam)
        intent.putExtra("idAwayTeam", idAwayTeam)
        intent.putExtra("idEvent", idEvent)
        context.startActivity(intent)
    }


    class ViewHolder (view : View) :RecyclerView.ViewHolder(view){
        val date = view.tvDateMatchFavorite
        val homeTeam = view.tvHomeTeamMatchFavorite
        val awayTeam = view.tvAwayTeamMatchFavorite
        val homeScore = view.tvHomeScoreMatchFavorite
        val awayScore = view.tvAwayScoreMatchFavorite

        val card = view.cardMatchFavorite
    }
}