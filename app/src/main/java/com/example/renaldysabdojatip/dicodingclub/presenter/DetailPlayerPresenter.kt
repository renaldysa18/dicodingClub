package com.example.renaldysabdojatip.dicodingclub.presenter

import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiService
import com.example.renaldysabdojatip.dicodingclub.Model.Player
import com.example.renaldysabdojatip.dicodingclub.ui.CoroutineContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.DetailPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPlayerPresenter(val view : DetailPlayerView, val apiRespository: ApiRespository, val gson: Gson,
                            val context : CoroutineContextProvider = CoroutineContextProvider()){
    fun getPlayer(id : String){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRespository.request(ApiService.getPlayerDetail(id)).await(), Player::class.java)
            view.showDataPlayer(data.playerObject)
            view.hideLoading()
        }
    }
}