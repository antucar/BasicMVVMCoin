package com.example.basicmvvm.service
import com.example.basicmvvm.model.CoinModel
import com.example.basicmvvm.model.MessageModel
import io.reactivex.rxjava3.core.Single

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Response as Response1

class CoinApiService {

    private val BaseUrl = "https://api.coincap.io/"
    private val api = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(CoinApi::class.java)



    fun getData(): Single<List<CoinModel>> {
        return api.getCoins().map { it.getAsJsonArray("data") }
            .map { jsonArray ->
                val coinList = mutableListOf<CoinModel>()
                jsonArray.forEach { jsonElement ->
                    val coinObject = jsonElement.asJsonObject
                    val coinModel = CoinModel(
                        coinObject.get("id")?.asString,
                        coinObject.get("rank")?.let { if (it.isJsonNull) 0 else it.asInt },
                        coinObject.get("symbol")?.asString,
                        coinObject.get("name")?.asString,
                        coinObject.get("supply")?.let { if (it.isJsonNull) 0.0 else it.asDouble },
                        coinObject.get("maxSupply")?.let { if (it.isJsonNull) 0.0 else it.asDouble },
                        coinObject.get("marketCapUsd")?.let { if (it.isJsonNull) 0.0 else it.asDouble },
                        coinObject.get("volumeUsd24Hr")?.let { if (it.isJsonNull) 0.0 else it.asDouble },
                        coinObject.get("priceUsd")?.let { if (it.isJsonNull) 0.0 else it.asDouble },
                        coinObject.get("changePercent24Hr")?.let { if (it.isJsonNull) 0.0 else it.asDouble },
                        coinObject.get("vwap24Hr")?.let { if (it.isJsonNull) 0.0 else it.asDouble }

                    )
                    coinList.add(coinModel)
                }
                coinList
            }
    }


    private val BaseUrlApi = "gpt.com"
    private val apiAi = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(CoinApi::class.java)

    fun postApi(): Single <List<MessageModel>> {
        return Response1
        
    }




}