package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.Model.TeamObject

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showData(data : List<TeamObject>)
}