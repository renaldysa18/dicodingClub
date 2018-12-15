package com.example.renaldysabdojatip.dicodingclub.Model

import com.google.gson.annotations.SerializedName

data class Search(
        @SerializedName("teams")
        val teamObject : List<TeamObject>
)