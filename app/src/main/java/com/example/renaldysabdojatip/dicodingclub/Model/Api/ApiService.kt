package com.example.renaldysabdojatip.dicodingclub.Model.Api

import com.example.renaldysabdojatip.dicodingclub.R

object ApiService{
    var api_key = "https://www.thesportsdb.com"

    fun getlastMatch(lastMatch : String) : String{
        return api_key+"/api/v1/json/1/eventspastleague.php?id="+lastMatch
    }

    fun getNextMatch(nextMatch : String) : String{
        return api_key+"/api/v1/json/1/eventsnextleague.php?id="+nextMatch
    }

    fun getImageTeam(idTeam : String) : String{
        return api_key+"/api/v1/json/1/lookupteam.php?id="+idTeam
    }

    fun getListTeam(team : String) : String{
        return api_key+"/api/v1/json/1/search_all_teams.php?l="+team
    }

    fun getTeamDetail(id : String) :String{
        return api_key+"/api/v1/json/1/lookupteam.php?id="+id
    }

    fun getEventDetail(id : String) :String {
        return api_key+"/api/v1/json/1/lookupevent.php?id="+id
    }

    fun getPlayerDetail(id : String) : String{
        return api_key+"/api/v1/json/1/lookup_all_players.php?id="+id
    }

    fun getSearchTeam(id : String) : String{
        return api_key+"/api/v1/json/1/searchteams.php?t="+id
    }
}