package com.example.renaldysabdojatip.dicodingclub.view

import com.example.renaldysabdojatip.dicodingclub.Model.MatchObject

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showDataMatch(data : List<MatchObject>)
}