package com.example.renaldysabdojatip.dicodingclub.ui.Activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.TeamObject
import com.example.renaldysabdojatip.dicodingclub.Model.db.FavoriteTeam
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.R.menu.menu_favorite
import com.example.renaldysabdojatip.dicodingclub.presenter.DetailTeamPresenter
import com.example.renaldysabdojatip.dicodingclub.view.DetailTeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_team.*
import com.example.renaldysabdojatip.dicodingclub.Model.db.database
import com.example.renaldysabdojatip.dicodingclub.R.drawable.ic_add_to_favorite
import com.example.renaldysabdojatip.dicodingclub.R.drawable.ic_added_to_favorite
import com.example.renaldysabdojatip.dicodingclub.adapter.tabs.TabDetailTeamAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeam : AppCompatActivity(), DetailTeamView {

    lateinit var idTeam: String
    lateinit var presenter: DetailTeamPresenter
    lateinit var team: TeamObject

    private var menuItem : Menu? = null
    private var isFavorite : Boolean= false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavoriteTeam()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        supportActionBar?.title = "Tim Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Log.d("DetailTeamBadge", team.strTeamBadge.toString())


        val apiRequest = ApiRespository()
        val gson = Gson()

        presenter = DetailTeamPresenter(this, apiRequest, gson)

        val getExtra: Bundle = intent.extras
        if (getExtra != null) {
            idTeam = getExtra.getString("idTeam")

        }

        favoriteState()
        presenter.getTeam(idTeam)

        val tabAdapter = TabDetailTeamAdapter(idTeam,supportFragmentManager)
        viewPagerMainTeamDetail.adapter = tabAdapter
        tabsMainTeamDetail.setupWithViewPager(viewPagerMainTeamDetail)
    }

    override fun showLoading() {
        progressDetailTeam.visibility = View.VISIBLE
        layoutDetailTeam.visibility = View.GONE
    }

    override fun hideLoading() {
        progressDetailTeam.visibility = View.GONE
        layoutDetailTeam.visibility = View.VISIBLE
    }

    override fun showData(data: List<TeamObject>) {
        team = TeamObject(
                data[0].idTeam,
                data[0].strTeam,
                data[0].strDescriptionEN,
                data[0].strTeamBadge,
                data[0].strStadium,
                data[0].strLeague,
                data[0].idLeague
        )
        Glide.with(applicationContext)
                .load(data[0].strTeamBadge)
                .into(imgvDetailTeam)

        tvNamaTeamDetail.text = data[0].strTeam
        tvStadiumDetailTeam.text = data[0].strStadium
    }


    fun addToFavoriteTeam(){
        try {
            Log.d("DataTeamOri", team.toString())
            database.use{
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to team.idTeam,
                        FavoriteTeam.TEAM_NAME to team.strTeam,
                        FavoriteTeam.TEAM_DESC to team.strDescriptionEN,
                        FavoriteTeam.TEAM_BADGE to team.strTeamBadge,
                        FavoriteTeam.TEAM_STADIUM to team.strStadium,
                        FavoriteTeam.TEAM_LEAGUE to team.strLeague,
                        FavoriteTeam.TEAM_LEAGUE_ID to team.idLeague
                )
            }
            applicationContext.toast("Added to favorite")
        } catch (e : SQLiteConstraintException){
            applicationContext.toast(e.localizedMessage)
        }
    }

    fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to idTeam)
            }
            applicationContext.toast("Removed to favorite")
        } catch (e : SQLiteConstraintException){
            applicationContext.toast(e.localizedMessage)
        }
    }

    fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
        }
    }

    fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if(!favorite.isEmpty()){
                isFavorite = true
            }
        }
    }
}
