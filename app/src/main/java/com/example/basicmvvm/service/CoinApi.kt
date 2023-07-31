package com.example.basicmvvm.service

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface CoinApi {
    @GET("v2/assets")
    fun getCoins(): Single<JsonObject>

    @POST()
    fun sendMessage(): Single<String>
}