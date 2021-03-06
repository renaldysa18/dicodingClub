package com.example.renaldysabdojatip.dicodingclub.presenter

import android.util.Log
import com.example.renaldysabdojatip.dicodingclub.model.api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.model.api.ApiService
import com.example.renaldysabdojatip.dicodingclub.model.Search
import com.example.renaldysabdojatip.dicodingclub.model.Team
import com.example.renaldysabdojatip.dicodingclub.ui.CoroutineContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(val view: TeamView, val apiRespository: ApiRespository, val gson: Gson,
                    val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getListTeam(team: String, search: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {

            if (search == "empty") {
                val listTeam = gson.fromJson(apiRespository.request(ApiService.getListTeam(team)).await(), Team::class.java)
                view.getTeam(listTeam.teamObject)
                view.hideLoading()
            } else if(team != "empty" && search != "empty") {
                val searcTeam = gson.fromJson(apiRespository.request(ApiService.getSearchTeam(search)).await(), Search::class.java )
                view.getTeam(searcTeam.teamObject)
                view.hideLoading()
            }
        }
    }
}