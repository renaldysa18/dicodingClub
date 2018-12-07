package com.example.renaldysabdojatip.dicodingclub.Model

import com.google.gson.annotations.SerializedName

data class MatchObject(
        @SerializedName("idEvent")
        var idEvent: String?,
        @SerializedName("strHomeTeam")
        var strHomeTeam: String?,
        @SerializedName("strAwayTeam")
        var strAwayTeam: String?,
        @SerializedName("intHomeScore")
        var intHomeScore: String?,
        @SerializedName("intAwayScore")
        var intAwayScore: String?,
        @SerializedName("dateEvent")
        var dateEvent: String?,
        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String?,
        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String?,
        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String?,
        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String?,
        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String?,
        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String?,
        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String?,
        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String?,
        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String?,
        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String?,
        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String?,
        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String?,
        @SerializedName("strHomeFormation")
        var strHomeFormation: String?,
        @SerializedName("strAwayFormation")
        var strAwayFormation: String?,
        @SerializedName("idHomeTeam")
        var idHomeTeam: String?,
        @SerializedName("idAwayTeam")
        var idAwayTeam : String?,
        @SerializedName("strEvent")
        var strEvent : String?

)