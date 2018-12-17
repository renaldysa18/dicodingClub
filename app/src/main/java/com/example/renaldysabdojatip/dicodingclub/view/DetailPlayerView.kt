package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.model.PlayerObject

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showDataPlayer(data : List<PlayerObject>)
}