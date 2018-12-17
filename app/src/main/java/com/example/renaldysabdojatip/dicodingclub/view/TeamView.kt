package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.model.TeamObject

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun getTeam(dataTeam : List<TeamObject>)
}