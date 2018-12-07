package com.example.renaldysabdojatip.dicodingclub.Model

import com.google.gson.annotations.SerializedName

data class TeamObject (
        @SerializedName("idTeam")
        var idTeam : String?,
        @SerializedName("strTeam")
        var strTeam : String?,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN : String?,
        @SerializedName("strTeamBadge")
        var strTeamBadge : String?,
        @SerializedName("strStadium")
        var strStadium : String?,
        @SerializedName("strLeague")
        var strLeague : String?,
        @SerializedName("idLeague")
        var idLeague : String?


)