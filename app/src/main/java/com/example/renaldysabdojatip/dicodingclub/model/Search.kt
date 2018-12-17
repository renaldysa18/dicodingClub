package com.example.renaldysabdojatip.dicodingclub.model

import com.google.gson.annotations.SerializedName

data class Search(
        @field:SerializedName("teams")
        val teamObject : List<TeamObject>?= null,

        @field:SerializedName("event")
        val event: List<MatchObject>? = null
)