package com.example.renaldysabdojatip.dicodingclub.model

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("player")
        val playerObject : List<PlayerObject>
)