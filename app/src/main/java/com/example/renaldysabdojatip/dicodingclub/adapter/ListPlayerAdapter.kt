package com.example.renaldysabdojatip.dicodingclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.renaldysabdojatip.dicodingclub.Model.PlayerObject
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.ui.Activity.DetailPlayer
import kotlinx.android.synthetic.main.list_player.view.*

class ListPlayerAdapter(val items: List<PlayerObject>, val context: Context) : RecyclerView.Adapter<ListPlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_player, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val list = items.get(p1)

        if (list.strCutOut != null) {
            Glide.with(context)
                    .load(list?.strCutOut)
                    .into(p0.imgvPlayer)
        } else {
            Glide.with(context)
                    .load(R.drawable.profile_man)
                    .into(p0.imgvPlayer)
        }

        p0?.tvNamePlayer?.text = list?.strPlayer
        p0?.tvPosisiPlayer?.text = list?.strPosition

        val idTeam : String = list?.idTeam.toString()
        val playerName : String = list?.strPlayer.toString()

        p0?.cardPlayer?.setOnClickListener(View.OnClickListener {v ->
            toDetailPlayer(context,idTeam, playerName )
        })
    }

    private fun toDetailPlayer(context: Context, idTeam: String,playerName :String) {
        val intent  = Intent(context, DetailPlayer::class.java)
        intent.putExtra("idTeam", idTeam)
        intent.putExtra("playerName", playerName)
        context?.startActivity(intent)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardPlayer = view.cardListPlayer
        val imgvPlayer = view.imgv_list_player
        val tvNamePlayer = view.tv_name_player_list
        val tvPosisiPlayer = view.tv_posisi_player_list
    }
}