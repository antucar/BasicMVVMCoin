package com.example.basicmvvm.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MessageModel(val role: String, val content: String) {
    @PrimaryKey(autoGenerate = true)
    var messageid: Int = 0
}