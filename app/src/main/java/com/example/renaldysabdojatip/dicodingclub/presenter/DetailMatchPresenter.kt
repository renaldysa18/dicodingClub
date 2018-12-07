package com.example.renaldysabdojatip.dicodingclub.presenter

import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiService
import com.example.renaldysabdojatip.dicodingclub.Model.Match
import com.example.renaldysabdojatip.dicodingclub.Model.PictTeam
import com.example.renaldysabdojatip.dicodingclub.ui.CoroutineContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.DetailPictMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(val pictMatchView: DetailPictMatchView, val apiRespository: ApiRespository, val gson: Gson,
                           val context: CoroutineContextProvider = CoroutineContextProvider()
                           ) {
    fun getPictTeam(idhome: String, idaway: String) {
        pictMatchView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val pictHome = gson.fromJson(apiRespository.request(ApiService.getImageTeam(idhome)).await(), PictTeam::class.java)

            val pictAway = gson.fromJson(apiRespository.request(ApiService.getImageTeam(idaway)).await(), PictTeam::class.java)

            pictMatchView.hideLoading()
            pictMatchView.showImageTeam(pictHome.pictTeam, pictAway.pictTeam)

        }
    }

    fun getEvent(idEvent: String) {
        pictMatchView.showLoading()
        GlobalScope.launch (Dispatchers.Main){
            val data = gson.fromJson(apiRespository.request(ApiService.getEventDetail(idEvent)).await(), Match::class.java)
            pictMatchView.hideLoading()
            pictMatchView.showDetailEvent(data.matchObject)
        }
    }

}