package com.example.renaldysabdojatip.dicodingclub.model

import com.google.gson.annotations.SerializedName

data class PlayerObject(
        @SerializedName("idPlayer")
        val idPlayer : String?,
        @SerializedName("idTeam")
        val idTeam : String?,
        @SerializedName("strNationality")
        val strNationality : String?,
        @SerializedName("strPlayer")
        val strPlayer : String?,
        @SerializedName("strTeam")
        val strTeam: String?,
        @SerializedName("strDescriptionEN")
        val strDescriptionEN : String?,
        @SerializedName("strHeight")
        val strHeight : String?,
        @SerializedName("strWeight")
        val strWeight : String?,
        @SerializedName("strPosition")
        val strPosition : String?,
        @SerializedName("strThumb")
        val strThumb : String?,
        @SerializedName("strCutout")
        val strCutOut : String?,
        @SerializedName("strFanart1")
        val strFanart1 : String?
)