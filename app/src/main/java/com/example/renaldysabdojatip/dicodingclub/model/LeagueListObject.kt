package com.example.renaldysabdojatip.dicodingclub.model

import com.google.gson.annotations.SerializedName

data class LeagueListObject(
        @SerializedName("idLeague")
        val idLeague : String?,
        @SerializedName("strLeague")
        val strLeague : String?,
        @SerializedName("strSport")
        val strSport : String?
)