package com.example.renaldysabdojatip.dicodingclub

import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiService
import com.example.renaldysabdojatip.dicodingclub.Model.Match
import com.example.renaldysabdojatip.dicodingclub.Model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.presenter.MatchPresenter
import com.example.renaldysabdojatip.dicodingclub.ui.TestContextProvider
import com.example.renaldysabdojatip.dicodingclub.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRespository: ApiRespository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRespository, gson, TestContextProvider())

    }

    @Test
    fun getMatch() {
        val event: MutableList<MatchObject> = mutableListOf()
        val response = Match(event)
        val matchId = "4328"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRespository
                    .request(ApiService.getNextMatch(matchId)).await(),
                    Match::class.java)
            ).thenReturn(response)

            presenter.getEventNext(matchId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDataMatch(event)
            Mockito.verify(view).hideLoading()
        }
    }

}

