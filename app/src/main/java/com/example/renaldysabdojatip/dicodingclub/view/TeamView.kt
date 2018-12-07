package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.Model.TeamObject

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun getTeam(dataTeam : List<TeamObject>)
}