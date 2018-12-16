package com.example.renaldysabdojatip.dicodingclub.Model

import com.google.gson.annotations.SerializedName

data class Search(
        @SerializedName("teams")
        val teamObject : List<TeamObject>,
        @field:SerializedName("events")
        val events: List<MatchObject>? = null,

        @field:SerializedName("event")
        val event: List<MatchObject>? = null
)