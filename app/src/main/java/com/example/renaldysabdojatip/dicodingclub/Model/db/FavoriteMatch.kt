package com.example.renaldysabdojatip.dicodingclub.Model.db

data class FavoriteMatch(val id: Long?,
                         val eventId :String?,
                         val homeScore: String?,
                         val awayScore:String?,
                         val eventDate : String?,
                         val homeLineupGoalkeeper : String?,
                         val awayLineupGoalkeeper : String?,
                         val homeGoalDetails : String?,
                         val awayGoalDetails : String?,
                         val homeLineupDefense : String?,
                         val awayLineupDefense : String?,
                         val homeLineupMidfield : String?,
                         val awayLineupMidfield : String?,
                         val homeLineupForward : String?,
                         val awayLineupForward : String?,
                         val homeLineupSubs : String?,
                         val awayLineupSubs : String?,
                         val homeFormation : String?,
                         val awayFormation : String?,
                         val homeTeamId : String?,
                         val awayTeamId : String?,
                         val strEvent : String?
                         ){
    companion object {
        const val FAVORITE_TABLE_MATCH : String = "FAVORITE_TABLE_TEAM"
        const val ID : String = "ID_"
        const val EVENT_ID : String = "EVENT_ID"
        const val HOME_SCORE : String = "HOME_SCORE"
        const val AWAY_SCORE : String = "AWAY_SCORE"
        const val EVENT_DATE : String = "EVENT_DATE"
        const val HOME_LINEUP_GOALKEEPER = "HOME_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_GOALKEEPER = "AWAY_LINEUP_GOALKEEPER"
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETALS"
        const val HOME_LINEUP_DEFENSE = "HOME_LINEUP_DEFENSE"
        const val AWAY_LINEUP_DEFENSE = "AWAY_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELD = "HOME_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_MIDFIELD = "AWAY_LINEUP_MIDFIELD"
        const val HOME_LINEUP_FORWARD = "HOME_LINEUP_FORWARD"
        const val AWAY_LINEUP_FORWARD = "AWAY_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBS = "HOME_LINEUP_SUBS"
        const val AWAY_LINEUP_SUBS = "AWAY_LINEUP_SUBS"
        const val HOME_FORMATION = "HOME_FORMATION"
        const val AWAY_FORMATION = "AWAY_FORMATION"
        const val HOME_TEAM_ID = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val STR_EVENT = "STR_EVENT"
     }

}