package com.example.renaldysabdojatip.dicodingclub

import com.example.renaldysabdojatip.dicodingclub.model.api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.model.api.ApiService
import com.example.renaldysabdojatip.dicodingclub.model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.model.PictTeam
import com.example.renaldysabdojatip.dicodingclub.model.PictTeamObject
import com.example.renaldysabdojatip.dicodingclub.presenter.DetailMatchPresenter
import com.example.renaldysabdojatip.dicodingclub.ui.TestContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.DetailPictMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {
    @Mock
    private lateinit var view: DetailPictMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRespository

    private lateinit var presenter: DetailMatchPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDetailEventList() {
        val event: MutableList<MatchObject> = mutableListOf()
        val list: MutableList<PictTeamObject> = mutableListOf()
        val response = PictTeam(list)
        val matchId = "441613"

        GlobalScope.launch {
            `when`(gson.fromJson(
                    apiRepository.request(ApiService.getEventDetail(matchId)).await(),
                    PictTeam::class.java)).thenReturn(response)
            presenter.getEvent(matchId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailEvent(event)
            Mockito.verify(view).hideLoading()
        }
    }
}