package com.example.renaldysabdojatip.dicodingclub.Model

import com.google.gson.annotations.SerializedName

data class LeagueList(
        @SerializedName("leagues")
        val leagueObject : List<LeagueListObject>
)