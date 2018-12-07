package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.Model.PlayerObject

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showDataPlayer(data : List<PlayerObject>)
}