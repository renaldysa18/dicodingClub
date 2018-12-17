package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.model.PictTeamObject

interface  DetailPictMatchView {
    fun showLoading()
    fun hideLoading()
    fun showImageTeam(datahome: List<PictTeamObject>, dataAway: List<PictTeamObject>)
    fun showDetailEvent(idEvent : List<MatchObject>)
}