package com.example.renaldysabdojatip.dicodingclub.ui.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.TeamObject

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.R.array.league
import com.example.renaldysabdojatip.dicodingclub.adapter.ListTeamAdapter
import com.example.renaldysabdojatip.dicodingclub.presenter.TeamPresenter
import com.example.renaldysabdojatip.dicodingclub.view.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list_team.*
import kotlinx.android.synthetic.main.fragment_list_team.view.*

class ListTeamFragment : Fragment(), TeamView {
    private var teamObjects : MutableList<TeamObject> = mutableListOf()
    private lateinit var presenter : TeamPresenter
    private lateinit var adapter  : ListTeamAdapter
    private lateinit var progresbar : ProgressBar
    private lateinit var layout : LinearLayout
    private lateinit var leagueName : String

    override fun showLoading() {
        progresbar.visibility = View.VISIBLE
        layout.visibility = View.GONE
    }

    override fun hideLoading() {
        progresbar.visibility =View.GONE
        layout.visibility = View.VISIBLE
    }

    override fun getTeam(data: List<TeamObject>) {
        teamObjects.clear()
        data?.let {
            teamObjects.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_list_team, container, false)

        progresbar = v.progressListTeam
        layout = v.layoutListTeam

        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = TeamPresenter(this, apiRequest, gson)


        val spinnerItem = resources.getStringArray(league)
        v.dropLeague.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,spinnerItem)

        v.dropLeague?.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = v.dropLeague.selectedItem.toString()
                presenter.getListTeam(leagueName)
            }

        }

        v.recyclerListTeam.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        adapter = ListTeamAdapter(teamObjects, requireContext())
        v.recyclerListTeam.adapter = adapter

        return v
    }


}
