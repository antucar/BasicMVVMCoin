package com.example.basicmvvm.service


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basicmvvm.model.ChatbotResponseEntity


@Database(entities = arrayOf(ChatbotResponseEntity::class), version = 1)
abstract class ChatbotResponseDatabase: RoomDatabase() {

    abstract fun ChatbotResponseDao(): ChatbotResponseDao


    companion object {
        @Volatile private var instance: ChatbotResponseDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }
        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,ChatbotResponseDatabase::class.java,"ResponseDatabase"
        ).build()
    }




}