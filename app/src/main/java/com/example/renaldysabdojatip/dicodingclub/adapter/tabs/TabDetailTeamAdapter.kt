package com.example.renaldysabdojatip.dicodingclub.adapter.tabs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.renaldysabdojatip.dicodingclub.ui.Fragment.team.DetailPlayerFragment
import com.example.renaldysabdojatip.dicodingclub.ui.Fragment.team.DetailTeamFragment


class TabDetailTeamAdapter(val idTeam : String,fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0 -> {
                overviewTeam(idTeam)
            }
            else -> {
                listPlayer(idTeam)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Overview"
            else -> "Player"
        }
    }
    companion object {
        const val KEY_ID = "ID_TEAM"

        fun overviewTeam(id: String) : DetailTeamFragment{
            val bindData = Bundle()
            bindData.putString(KEY_ID, id)

            val teamFragment = DetailTeamFragment()
            teamFragment.arguments = bindData
            return teamFragment
        }

        fun listPlayer(id : String) : DetailPlayerFragment{
            val bindData = Bundle()
            bindData.putString(KEY_ID, id)
            val playerFragment = DetailPlayerFragment()
            playerFragment.arguments = bindData
            return playerFragment
        }
    }
}