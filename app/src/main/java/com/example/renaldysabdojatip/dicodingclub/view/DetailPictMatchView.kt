package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.Model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.Model.PictTeamObject

interface  DetailPictMatchView {
    fun showLoading()
    fun hideLoading()
    fun showImageTeam(datahome: List<PictTeamObject>, dataAway: List<PictTeamObject>)
    fun showDetailEvent(idEvent : List<MatchObject>)
}