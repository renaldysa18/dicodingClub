package com.example.renaldysabdojatip.dicodingclub.ui.fragment.home


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.*
import com.example.renaldysabdojatip.dicodingclub.model.api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.model.MatchObject

import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.R.array.league
import com.example.renaldysabdojatip.dicodingclub.adapter.LastMatchAdapter
import com.example.renaldysabdojatip.dicodingclub.presenter.MatchPresenter
import com.example.renaldysabdojatip.dicodingclub.view.MatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import org.jetbrains.anko.support.v4.toast


class LastMatchFragment : Fragment(), MatchView {


    private var matchObjects: MutableList<MatchObject> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: LastMatchAdapter
    private lateinit var progresbar: ProgressBar
    private lateinit var searchView: SearchView
    private lateinit var spinner : Spinner
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private var empty = "empty"
    private lateinit var leagueName : String

    override fun showLoading() {
        progresbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progresbar.visibility = View.GONE
    }

    override fun showDataMatch(data: List<MatchObject>?) {
        if (data == null) {
            toast("Data Tidak ada")
        } else if (data != null) {
            matchObjects.clear()
            data?.let {
                matchObjects.addAll(data)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_last_match, container, false)
        progresbar = v.progressLastMatch
        spinner = v.dropLeagueLastMatch

        v.recyclerLastMatch.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = MatchPresenter(this, apiRequest, gson)

        val spinnerItem = resources.getStringArray(league)
        spinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, spinnerItem)

        spinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                if(leagueName.equals(getString(R.string.english_pemier_league))){
                    presenter.getEventLast(getString(R.string.english_premier_league_id), empty)
                }
                if(leagueName.equals(getString(R.string.english_league_championship))){
                    presenter.getEventLast(getString(R.string.english_league_championship_id), empty)
                }
                if(leagueName.equals(getString(R.string.german_bundes_liga))){
                    presenter.getEventLast(getString(R.string.german_bundes_liga_id), empty)
                }
                if(leagueName.equals(getString(R.string.italian_serie_A))){
                    presenter.getEventLast(getString(R.string.italian_serie_A_id), empty)
                }
                if(leagueName.equals(getString(R.string.french_ligue_1))){
                    presenter.getEventLast(getString(R.string.french_ligue_1_id), empty)
                }
                if(leagueName.equals(getString(R.string.spanish_la_liga))){
                    presenter.getEventLast(getString(R.string.spanish_la_liga_id), empty)
                }


            }

        }

        adapter = LastMatchAdapter(matchObjects, requireContext())
        v.recyclerLastMatch.adapter = adapter

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search_event, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.btn_search_event)
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText!!.isNotEmpty()) {
                        matchObjects.clear()
                        val search: String = newText.toLowerCase()
                        presenter.getEventLast(empty, search)
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
            R.id.btn_search_event ->
                // Not implemented here
                return false
            else -> {
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }
}
