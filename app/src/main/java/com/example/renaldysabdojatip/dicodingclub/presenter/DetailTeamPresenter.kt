package com.example.renaldysabdojatip.dicodingclub.presenter

import com.example.renaldysabdojatip.dicodingclub.model.api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.model.api.ApiService
import com.example.renaldysabdojatip.dicodingclub.model.Team
import com.example.renaldysabdojatip.dicodingclub.ui.CoroutineContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.DetailTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(val view: DetailTeamView, val apiRespository: ApiRespository, val gson: Gson,
                          val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeam(team: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRespository.request(ApiService.getTeamDetail(team)).await(), Team::class.java)
            view.hideLoading()
            view.showData(data.teamObject)

        }
    }
}