package com.example.renaldysabdojatip.dicodingclub.Model

import com.google.gson.annotations.SerializedName

data class  Match(
        @SerializedName("events")
        val matchObject : List<MatchObject>
)