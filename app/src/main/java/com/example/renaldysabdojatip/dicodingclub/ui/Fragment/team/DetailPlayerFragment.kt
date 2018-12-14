package com.example.renaldysabdojatip.dicodingclub.ui.Fragment.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.tabs.TabDetailTeamAdapter.Companion.KEY_ID
import kotlinx.android.synthetic.main.fragment_detail_player.view.*

class DetailPlayerFragment : Fragment() {

    private lateinit var teamId : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_detail_player, container, false)

        val bindData = arguments
        teamId = bindData?.getString(KEY_ID) ?: "ID_TEAM"

        v.tvPlayer.text = teamId

        return v
    }


}
