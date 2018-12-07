package com.example.renaldysabdojatip.dicodingclub.presenter

import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiService
import com.example.renaldysabdojatip.dicodingclub.Model.Team
import com.example.renaldysabdojatip.dicodingclub.ui.CoroutineContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(val view: TeamView, val apiRespository: ApiRespository, val gson: Gson,
                    val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getListTeam(team: String) {
        view.showLoading()
        GlobalScope.launch (Dispatchers.Main){
            val listTeam = gson.fromJson(apiRespository.request(ApiService.getListTeam(team)).await(), Team::class.java)
            view.getTeam(listTeam.teamObject)
            view.hideLoading()
        }
    }
}