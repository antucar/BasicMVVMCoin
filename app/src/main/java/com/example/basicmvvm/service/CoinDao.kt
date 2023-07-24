package com.example.basicmvvm.service


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.basicmvvm.model.CoinModel

@Dao
interface CoinDao {

    @Insert
    suspend fun insertAll(vararg coins: CoinModel): List<Long>

    @Query("SELECT * FROM CoinModel")
    suspend fun getAllCoins(): List<CoinModel>

    @Query("SELECT * FROM coinmodel WHERE uuid = :coinid")
    suspend fun getCoins(coinid: Int): CoinModel

    @Query("DELETE FROM coinmodel")
    suspend fun deleteCoins()



}