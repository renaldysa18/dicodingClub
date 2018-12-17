package com.example.renaldysabdojatip.dicodingclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.renaldysabdojatip.dicodingclub.ui.Activity.DetailMatch
import com.example.renaldysabdojatip.dicodingclub.Model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.R
import kotlinx.android.synthetic.main.list_last_match.view.*


class LastMatchAdapter(val items : List<MatchObject>, val context: Context) : RecyclerView.Adapter<LastMatchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.list_last_match, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val list = items.get(p1)

        p0?.dateLastMatch?.text = list.dateEvent

        p0?.homeScore?.text = list.intHomeScore

        p0?.awayScore?.text = list.intAwayScore
        p0?.event?.text = list.strEvent

        val idHomeTeam : String = list?.idHomeTeam.toString()
        val idAwayTeam : String = list?.idAwayTeam.toString()
        val idEvent : String = list?.idEvent.toString()

        p0?.cardLastMatch?.setOnClickListener{ v->
            toDetailMatch(context, idHomeTeam, idAwayTeam, idEvent)
        }
    }

    private fun toDetailMatch(context: Context, idHomeTeam: String, idAwayTeam: String, idEvent: String?) {
        val intent = Intent(context, DetailMatch::class.java)
        intent.putExtra("idHomeTeam", idHomeTeam)
        intent.putExtra("idAwayTeam", idAwayTeam)
        intent.putExtra("idEvent", idEvent)
        context?.startActivity(intent)
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val cardLastMatch = view.cardLastMatch
        val dateLastMatch = view.tvDateLastMatch
        val homeScore = view.tvHomeScore
        val awayScore = view.tvAwayScore
        val event = view.tvEventLastMatch
    }
}