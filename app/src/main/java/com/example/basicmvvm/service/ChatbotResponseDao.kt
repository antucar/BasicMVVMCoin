
package com.example.basicmvvm.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basicmvvm.model.ChatbotResponseEntity

@Dao
interface ChatbotResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chatbotResponseEntity: ChatbotResponseEntity)


    @Query("SELECT * FROM chatbot_responses")
    suspend fun getAllResponse(): List<ChatbotResponseEntity>

}
