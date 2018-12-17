package com.example.renaldysabdojatip.dicodingclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.renaldysabdojatip.dicodingclub.model.db.FavoriteMatch
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.ui.activity.DetailMatch
import kotlinx.android.synthetic.main.list_match_favorite.view.*

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
        p0?.homeScore?.text = list?.homeScore
        p0?.awayScore?.text = list?.awayScore
        p0?.event?.text = list?.strEvent

        val idHomeTeam : String = list?.homeTeamId.toString()
        val idAwayTeam : String = list?.awayTeamId.toString()
        val idEvent : String = list?.eventId.toString()

        p0?.card?.setOnClickListener{ v->
            toDetailMatch(context, idHomeTeam, idAwayTeam, idEvent)
        }
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
        val homeScore = view.tvHomeScoreMatchFavorite
        val awayScore = view.tvAwayScoreMatchFavorite
        val event = view.tvEventMatchFavorite
        val card = view.cardMatchFavorite
    }
}