package com.example.basicmvvm.service

import androidx.room.Insert
import androidx.room.Query
import com.example.basicmvvm.model.MessageModel

interface MessageDao {

    @Insert
    fun insert(vararg message: MessageModel): List<Int>

    @Query("SELECT * FROM MessageModel")
    suspend fun getAllMessages(): List<MessageModel>

    @Query("SELECT * FROM MessageModel WHERE messageid = :messageid")
    suspend fun getCoins(messageid: Int): MessageModel

    @Query("DELETE FROM MessageModel")
    suspend fun deleteMessages()

}