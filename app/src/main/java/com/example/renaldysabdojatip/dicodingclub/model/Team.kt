package com.example.renaldysabdojatip.dicodingclub.model

import com.google.gson.annotations.SerializedName

data class Team (
        @SerializedName("teams")
        val teamObject : List<TeamObject>
)