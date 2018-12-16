package com.example.renaldysabdojatip.dicodingclub.ui.Activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.renaldysabdojatip.dicodingclub.Model.Api.ApiRespository
import com.example.renaldysabdojatip.dicodingclub.Model.MatchObject
import com.example.renaldysabdojatip.dicodingclub.Model.PictTeamObject
import com.example.renaldysabdojatip.dicodingclub.Model.db.FavoriteMatch
import com.example.renaldysabdojatip.dicodingclub.Model.db.database
import com.example.renaldysabdojatip.dicodingclub.R
import com.example.renaldysabdojatip.dicodingclub.R.drawable.ic_add_to_favorite
import com.example.renaldysabdojatip.dicodingclub.R.drawable.ic_added_to_favorite
import com.example.renaldysabdojatip.dicodingclub.R.id.add_to_favorite
import com.example.renaldysabdojatip.dicodingclub.R.menu.menu_favorite
import com.example.renaldysabdojatip.dicodingclub.presenter.DetailMatchPresenter
import com.example.renaldysabdojatip.dicodingclub.view.DetailPictMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailMatch : AppCompatActivity(), DetailPictMatchView {


    private lateinit var pictPresenter: DetailMatchPresenter
    lateinit var idHomeTeam: String
    lateinit var idAwayTeam: String
    lateinit var idEvent: String

    lateinit var pictHome: ImageView
    lateinit var pictAway: ImageView

    lateinit var match: MatchObject

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pictHome = imgvHomeLogo
        pictAway = imgvAwayLogo


        val apiRequest = ApiRespository()
        val gson = Gson()

        pictPresenter = DetailMatchPresenter(this, apiRequest, gson)
        val getExtra: Bundle = intent.extras
        if (getExtra != null) {
            idHomeTeam = getExtra.getString("idHomeTeam")
            idAwayTeam = getExtra.getString("idAwayTeam")
            idEvent = getExtra.getString("idEvent")
        }

        favoriteState()
        pictPresenter.getPictTeam(idHomeTeam, idAwayTeam)
        pictPresenter.getEvent(idEvent)


    }


    override fun showImageTeam(datahome: List<PictTeamObject>, dataAway: List<PictTeamObject>) {
        if (!isFinishing) {
            Glide.with(this)
                    .load(datahome.get(0).strTeamBadge)
                    .into(pictHome)

            Glide.with(this)
                    .load(dataAway.get(0).strTeamBadge)
                    .into(pictAway)
        }
    }

    override fun showLoading() {
        progressDetailMatch.visibility = View.VISIBLE
        layoutDetailMatch.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavoriteMatch() else addToFavoriteMatch()
                Log.d("favorite", "masuk")
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showDetailEvent(data: List<MatchObject>) {
        match = MatchObject(
                data[0].idEvent,
                data[0].intHomeScore,
                data[0].intAwayScore,
                data[0].dateEvent,
                data[0].strHomeLineupGoalkeeper,
                data[0].strAwayLineupGoalkeeper,
                data[0].strHomeGoalDetails,
                data[0].strAwayGoalDetails,
                data[0].strHomeLineupDefense,
                data[0].strAwayLineupDefense,
                data[0].strHomeLineupMidfield,
                data[0].strAwayLineupMidfield,
                data[0].strHomeLineupForward,
                data[0].strAwayLineupForward,
                data[0].strHomeLineupSubstitutes,
                data[0].strAwayLineupSubstitutes,
                data[0].strHomeFormation,
                data[0].strAwayFormation,
                data[0].idHomeTeam,
                data[0].idAwayTeam,
                data[0].strEvent
        )
        tvDateDetail?.text = data[0].dateEvent
        tvDetailHomeScore?.text = data[0].intHomeScore
        tvDetailAwayScore?.text = data[0].intAwayScore
        tvDetailFormationHome?.text = data[0].strHomeFormation
        tvDetailFormationAway?.text = data[0].strAwayFormation
        tvDetailGoalKeeperHome?.text = data[0].strHomeLineupGoalkeeper
        tvDetailGoalKeeperAway?.text = data[0].strAwayLineupGoalkeeper
        tvDetailDefenseHome?.text = data[0].strHomeLineupDefense
        tvDetailDefenseAway?.text = data[0].strAwayLineupDefense
        tvDetailMidfieldHome?.text = data[0].strHomeLineupMidfield
        tvDetailMidfieldAway?.text = data[0].strAwayLineupMidfield
        tvDetailForwardHome?.text = data[0].strHomeLineupForward
        tvDetailForwardAway?.text = data[0].strAwayLineupForward
        tvDetailSubstituesHome?.text = data[0].strHomeLineupSubstitutes
        tvDetailSubstituesAway?.text = data[0].strAwayLineupSubstitutes
        tvEventDetailMatch?.text = data[0].strEvent

    }

    override fun hideLoading() {
        progressDetailMatch.visibility = View.GONE
        layoutDetailMatch.visibility = View.VISIBLE
    }

    fun addToFavoriteMatch() {
        try {
            database.use {
                insert(
                        FavoriteMatch.FAVORITE_TABLE_MATCH,
                        FavoriteMatch.EVENT_ID to match.idEvent,
                        FavoriteMatch.HOME_SCORE to match.intHomeScore,
                        FavoriteMatch.AWAY_SCORE to match.intAwayScore,
                        FavoriteMatch.EVENT_DATE to match.dateEvent,
                        FavoriteMatch.HOME_LINEUP_GOALKEEPER to match.strHomeLineupGoalkeeper,
                        FavoriteMatch.AWAY_LINEUP_GOALKEEPER to match.strAwayLineupGoalkeeper,
                        FavoriteMatch.HOME_GOAL_DETAILS to match.strHomeGoalDetails,
                        FavoriteMatch.AWAY_GOAL_DETAILS to match.strAwayGoalDetails,
                        FavoriteMatch.HOME_LINEUP_DEFENSE to match.strHomeLineupDefense,
                        FavoriteMatch.AWAY_LINEUP_DEFENSE to match.strAwayLineupDefense,
                        FavoriteMatch.HOME_LINEUP_MIDFIELD to match.strHomeLineupMidfield,
                        FavoriteMatch.AWAY_LINEUP_MIDFIELD to match.strAwayLineupMidfield,
                        FavoriteMatch.HOME_LINEUP_FORWARD to match.strHomeLineupForward,
                        FavoriteMatch.AWAY_LINEUP_FORWARD to match.strAwayLineupForward,
                        FavoriteMatch.HOME_LINEUP_SUBS to match.strHomeLineupSubstitutes,
                        FavoriteMatch.AWAY_LINEUP_SUBS to match.strAwayLineupSubstitutes,
                        FavoriteMatch.HOME_FORMATION to match.strHomeFormation,
                        FavoriteMatch.AWAY_FORMATION to match.strAwayFormation,
                        FavoriteMatch.HOME_TEAM_ID to match.idHomeTeam,
                        FavoriteMatch.AWAY_TEAM_ID to match.idAwayTeam,
                        FavoriteMatch.STR_EVENT to match.strEvent
                )
                //Log.d("DataDetailMatch", )
            }
            applicationContext.toast("Added to match favorite")
        } catch (e: SQLiteConstraintException) {
            applicationContext.toast(e.localizedMessage)
        }
    }

    fun removeFromFavoriteMatch() {
        try {
            database.use {
                delete(FavoriteMatch.FAVORITE_TABLE_MATCH, "(EVENT_ID = {id})",
                        "id" to idEvent)
            }
            applicationContext.toast("Removed from favorite match")
        } catch (e: SQLiteConstraintException) {
            applicationContext.toast(e.localizedMessage)
        }
    }

    fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
        }
    }

    fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.FAVORITE_TABLE_MATCH)
                    .whereArgs("(EVENT_ID) = {id}", "id" to idEvent)

            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) {
                isFavorite = true
            }
        }
    }

}
