package com.example.renaldysabdojatip.dicodingclub.Model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) :ManagedSQLiteOpenHelper(context, "FavoriteTeam.db", null , 1){
    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_DESC to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT,
                FavoriteTeam.TEAM_STADIUM to TEXT,
                FavoriteTeam.TEAM_LEAGUE to TEXT,
                FavoriteTeam.TEAM_LEAGUE_ID to TEXT)

        db?.createTable(FavoriteMatch.FAVORITE_TABLE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
                FavoriteMatch.HOME_TEAM to TEXT,
                FavoriteMatch.AWAY_TEAM to TEXT,
                FavoriteMatch.HOME_SCORE to TEXT,
                FavoriteMatch.AWAY_SCORE to TEXT,
                FavoriteMatch.EVENT_DATE to TEXT,
                FavoriteMatch.HOME_LINEUP_GOALKEEPER to TEXT,
                FavoriteMatch.AWAY_LINEUP_GOALKEEPER to TEXT,
                FavoriteMatch.HOME_GOAL_DETAILS to TEXT,
                FavoriteMatch.AWAY_GOAL_DETAILS to TEXT,
                FavoriteMatch.HOME_LINEUP_DEFENSE to TEXT,
                FavoriteMatch.AWAY_LINEUP_DEFENSE to TEXT,
                FavoriteMatch.HOME_LINEUP_MIDFIELD to TEXT,
                FavoriteMatch.AWAY_LINEUP_MIDFIELD to TEXT,
                FavoriteMatch.HOME_LINEUP_FORWARD to TEXT,
                FavoriteMatch.AWAY_LINEUP_FORWARD to TEXT,
                FavoriteMatch.HOME_LINEUP_SUBS to TEXT,
                FavoriteMatch.AWAY_LINEUP_SUBS to TEXT,
                FavoriteMatch.HOME_FORMATION to TEXT,
                FavoriteMatch.AWAY_FORMATION to TEXT,
                FavoriteMatch.HOME_TEAM_ID to TEXT,
                FavoriteMatch.AWAY_TEAM_ID to TEXT,
                FavoriteMatch.STR_EVENT  to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)

        db?.dropTable(FavoriteMatch.FAVORITE_TABLE_MATCH, true)
    }
}
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)