package com.example.renaldysabdojatip.dicodingclub.ui.Fragment.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.NextMatchAdapter
import com.example.renaldysabdojatip.dicodingclub.presenter.MatchPresenter
import com.example.renaldysabdojatip.dicodingclub.view.MatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.view.*

class NextMatchFragment : Fragment(), MatchView {
    lateinit var progresbar : ProgressBar
    private var matchObjects : MutableList<MatchObject> = mutableListOf()
    private lateinit var presenter : MatchPresenter
    private lateinit var adapter  : NextMatchAdapter

    override fun showLoading() {
        progresbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progresbar.visibility = View.GONE
    }

    override fun showDataMatch(data: List<MatchObject>) {
        matchObjects.clear()
        data?.let {
            matchObjects.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_next_match, container, false)

        progresbar = v.progressNextMatch

        v.recyclerNextMatch.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = MatchPresenter(this, apiRequest, gson)
        presenter.getEventNext("4328")

        adapter = NextMatchAdapter(matchObjects, requireContext())
        v.recyclerNextMatch.adapter = adapter

        return v
    }


}
