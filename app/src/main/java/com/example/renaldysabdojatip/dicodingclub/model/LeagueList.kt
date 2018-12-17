package com.example.renaldysabdojatip.dicodingclub.model

import com.google.gson.annotations.SerializedName

data class LeagueList(
        @SerializedName("leagues")
        val leagueObject : List<LeagueListObject>
)