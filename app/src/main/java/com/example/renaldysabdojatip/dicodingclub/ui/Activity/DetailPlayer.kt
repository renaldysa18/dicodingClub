package com.example.renaldysabdojatip.dicodingclub.ui.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.PlayerObject
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.presenter.DetailPlayerPresenter
import com.example.renaldysabdojatip.dicodingclub.view.DetailPlayerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayer : AppCompatActivity(), DetailPlayerView {

    private lateinit var idTeam : String
    private lateinit var playerName : String
    private lateinit var presenter : DetailPlayerPresenter
    private lateinit var player : PlayerObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        val getExtra : Bundle = intent.extras
        if(getExtra != null){
            idTeam = getExtra.getString("idTeam")
            playerName = getExtra.getString("playerName")
        }

        supportActionBar?.title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = DetailPlayerPresenter(this, apiRequest, gson)


        presenter.getPlayer(idTeam)

    }

    override fun showLoading() {
        layout_player_detail.visibility = View.GONE
        progress_player_detail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        layout_player_detail.visibility = View.VISIBLE
        progress_player_detail.visibility = View.GONE
    }

    override fun showDataPlayer(data: List<PlayerObject>) {
        player = PlayerObject(
                data[0].idPlayer,
                data[0].idTeam,
                data[0].strNationality,
                data[0].strPlayer,
                data[0].strTeam,
                data[0].strDescriptionEN,
                data[0].strHeight,
                data[0].strWeight,
                data[0].strPosition,
                data[0].strThumb,
                data[0].strCutOut,
                data[0].strFanart1
        )

        Glide.with(this)
                .load(data[0].strFanart1)
                .into(imgv_detail_player)


        tv_weight_player.text = data[0].strWeight
        tv_height_player.text = data[0].strHeight
        tv_description_player.text = data[0].strDescriptionEN
        tv_posisi_player.text = data[0].strPosition

    }

}
