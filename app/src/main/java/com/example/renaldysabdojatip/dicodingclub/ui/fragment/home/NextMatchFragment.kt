package com.example.renaldysabdojatip.dicodingclub.ui.fragment.home


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.renaldysabdojatip.dicodingclub.model.api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.adapter.NextMatchAdapter
import com.example.renaldysabdojatip.dicodingclub.presenter.MatchPresenter
import com.example.renaldysabdojatip.dicodingclub.view.MatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment(), MatchView {
    lateinit var progresbar: ProgressBar
    private var matchObjects: MutableList<MatchObject> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: NextMatchAdapter
    private lateinit var searchView: SearchView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private var empty = "empty"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_next_match, container, false)

        progresbar = v.progressNextMatch

        v.recyclerNextMatch.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = MatchPresenter(this, apiRequest, gson)
        presenter.getEventNext("4328", empty)

        adapter = NextMatchAdapter(matchObjects, requireContext())
        v.recyclerNextMatch.adapter = adapter

        return v
    }


    override fun showLoading() {
        progresbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progresbar.visibility = View.GONE
    }

    override fun showDataMatch(data: List<MatchObject>?) {
        if (data == null) {
            toast("Data Tidak ditemukan")
        } else if (data != null) {
            matchObjects.clear()
            data?.let {
                matchObjects.addAll(data)
                adapter.notifyDataSetChanged()
            }
        }
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
                        presenter.getEventNext(empty, search)
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
