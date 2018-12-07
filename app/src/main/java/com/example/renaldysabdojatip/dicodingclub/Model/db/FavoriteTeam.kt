package com.example.renaldysabdojatip.dicodingclub.Model.db

data class FavoriteTeam(val id: Long?,
                        val teamId : String?,
                        val teamName : String?,
                        val teamDesc : String?,
                        val teamBadge : String?,
                        val teamStadium : String?,
                        val teamLeague : String?,
                        val teamLeagueId : String?){
    companion object {
        const val TABLE_FAVORITE_TEAM = "TABLE_FAVORITE_TEAM"
        const val ID : String = "ID_"
        const val TEAM_ID : String = "TEAM_ID"
        const val TEAM_NAME : String = "TEAM_NAME"
        const val TEAM_DESC : String = "TEAM_DESC"
        const val TEAM_BADGE : String = "TEAM_BADGE"
        const val TEAM_STADIUM : String = "TEAM_STADIUM"
        const val TEAM_LEAGUE : String = "TEAM_LEAGUE"
        const val TEAM_LEAGUE_ID :String = "TEAM_LEAGUE_ID"
    }
}