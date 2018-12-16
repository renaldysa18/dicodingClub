package com.example.renaldysabdojatip.dicodingclub.ui.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
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
import kotlinx.android.synthetic.main.fragment_list_team.view.*
import android.app.SearchManager
import android.content.Context
import android.support.v7.widget.SearchView
import android.util.Log


class ListTeamFragment : Fragment(), TeamView {
    private var teamObjects : MutableList<TeamObject> = mutableListOf()
    private lateinit var presenter : TeamPresenter
    private lateinit var adapter  : ListTeamAdapter
    private lateinit var progresbar : ProgressBar
    private lateinit var layout : LinearLayout
    private lateinit var leagueName : String
    private lateinit var searchView : SearchView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private var empty_input : String = "empty"

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
                presenter.getListTeam(leagueName, empty_input)
            }

        }

        v.recyclerListTeam.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        adapter = ListTeamAdapter(teamObjects, requireContext())
        v.recyclerListTeam.adapter = adapter

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search_team, menu)
        val searchItem : MenuItem = menu!!.findItem(R.id.btn_search)
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if(searchItem != null){
            searchView = searchItem.actionView as SearchView
        }
        if(searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if(newText!!.isNotEmpty()){
                        teamObjects.clear()
                        val search : String = newText.toLowerCase()
                        presenter.getListTeam(leagueName, search)
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.i("onQueryTextSubmit", query)

                    return true
                }
            }
            searchView.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.btn_search ->
                // Not implemented here
                return false
            else -> {
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }
}
