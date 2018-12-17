package com.example.renaldysabdojatip.dicodingclub.model

import com.google.gson.annotations.SerializedName

data class  PictTeam(
        @SerializedName("teams")
        val pictTeam : List<PictTeamObject>
)