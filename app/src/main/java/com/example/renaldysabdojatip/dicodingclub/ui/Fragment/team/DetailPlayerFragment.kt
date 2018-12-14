package com.example.renaldysabdojatip.dicodingclub.ui.Fragment.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.PlayerObject

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.ListPlayerAdapter
import com.example.renaldysabdojatip.dicodingclub.adapter.tabs.TabDetailTeamAdapter.Companion.KEY_ID
import com.example.renaldysabdojatip.dicodingclub.presenter.DetailPlayerPresenter
import com.example.renaldysabdojatip.dicodingclub.view.DetailPlayerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail_player.view.*

class DetailPlayerFragment : Fragment(), DetailPlayerView{

    private lateinit var teamId : String
    private var player : MutableList<PlayerObject> = mutableListOf()
    private lateinit var presenter : DetailPlayerPresenter
    private lateinit var adapter : ListPlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_detail_player, container, false)

        val bindData = arguments
        teamId = bindData?.getString(KEY_ID) ?: "ID_TEAM"

        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = DetailPlayerPresenter(this, apiRequest, gson)
        presenter.getPlayer(teamId)

        v.recyclerPlayerList.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        adapter = ListPlayerAdapter(player, requireContext())
        v.recyclerPlayerList.adapter = adapter

        return v
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showDataPlayer(data: List<PlayerObject>) {
        player.clear()
        data?.let {
            player.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

}
