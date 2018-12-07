package com.example.renaldysabdojatip.dicodingclub.presenter

import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiService
import com.example.renaldysabdojatip.dicodingclub.Model.Match
import com.example.renaldysabdojatip.dicodingclub.ui.CoroutineContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(val view: MatchView, val apiRespository: ApiRespository, val gson: Gson,
                     val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getEventLast(id: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRespository.request(ApiService.getlastMatch(id)).await(), Match::class.java)
            view.hideLoading()
            view.showDataMatch(data.matchObject)


        }


    }


    fun getEventNext(id: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRespository.request(ApiService.getNextMatch(id)).await(), Match::class.java)

            view.hideLoading()
            view.showDataMatch(data.matchObject)

        }
    }
}


