package com.example.renaldysabdojatip.dicodingclub.Model

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("player")
        val playerObject : List<PlayerObject>
)