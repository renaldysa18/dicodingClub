package com.example.renaldysabdojatip.dicodingclub.Model.Api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRespository {
    fun request(url : String) : Deferred<String> = GlobalScope.async{
       URL(url).readText()
    }
}