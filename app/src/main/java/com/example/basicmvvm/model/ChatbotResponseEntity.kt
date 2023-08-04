package com.example.basicmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "chatbot_responses")
data class ChatbotResponseEntity(
    @SerializedName("userMessage")
    @ColumnInfo("userMessage")
    var userMessage: String?,
    @SerializedName("chatbotResponse")
    @ColumnInfo("chatbotResponse")
    var chatbotResponse: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}