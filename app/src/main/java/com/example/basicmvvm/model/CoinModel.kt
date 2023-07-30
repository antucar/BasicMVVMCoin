package com.example.basicmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CoinModel(
    @ColumnInfo("id")
    @SerializedName("id")
    val id: String?,

    @ColumnInfo("rank")
    @SerializedName("rank")
    val rank: Int?,

    @ColumnInfo("coinname")
    @SerializedName("symbol")
    val coinname: String?,

    @ColumnInfo("name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo("supply")
    @SerializedName("supply")
    val supply: Double?,

    @ColumnInfo("maxSupply")
    @SerializedName("maxSupply")
    val maxSupply: Double?,

    @ColumnInfo("marketCapUsd")
    @SerializedName("marketCapUsd")
    val marketCapUsd: Double?,

    @ColumnInfo("volumeUsd24Hr")
    @SerializedName("volumeUsd24Hr")
    val volumeUsd24Hr: Double?,

    @ColumnInfo("currency")
    @SerializedName("priceUsd")
    val currency: Double?,

    @ColumnInfo("changePercent24Hr")
    @SerializedName("changePercent24Hr")
    val changePercent24Hr: Double?,

    @ColumnInfo("vwap24Hr")
    @SerializedName("vwap24Hr")
    val vwap24Hr: Double?,) {

    @PrimaryKey(autoGenerate = true)
        var uuid: Int = 0



}


