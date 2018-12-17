package com.example.renaldysabdojatip.dicodingclub.ui.fragment.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.renaldysabdojatip.dicodingclub.model.api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.model.TeamObject

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.tabs.TabDetailTeamAdapter.Companion.KEY_ID
import com.example.renaldysabdojatip.dicodingclub.presenter.DetailTeamPresenter
import com.example.renaldysabdojatip.dicodingclub.view.DetailTeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail_team.view.*

class DetailTeamFragment : Fragment(), DetailTeamView {

    private lateinit var teamId: String
    private lateinit var team: TeamObject
    private lateinit var presenter: DetailTeamPresenter
    private lateinit var tvOverview: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_detail_team, container, false)
        val bindData = arguments
        teamId = bindData?.getString(KEY_ID) ?: "ID_TEAM"

        tvOverview = v.tvOverview

        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = DetailTeamPresenter(this, apiRequest, gson)

        presenter.getTeam(teamId)

        return v
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showData(data: List<TeamObject>) {
        team = TeamObject(
                data[0].idTeam,
                data[0].strTeam,
                data[0].strDescriptionEN,
                data[0].strTeamBadge,
                data[0].strStadium,
                data[0].strLeague,
                data[0].idLeague
        )

        tvOverview.text = data[0].strDescriptionEN
    }


}
