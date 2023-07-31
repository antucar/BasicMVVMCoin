package com.example.basicmvvm.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basicmvvm.model.MessageModel

@Database(entities = arrayOf(MessageModel::class), version = 1)
abstract class MessageDatabase: RoomDatabase() {

    abstract fun MessageDao(): MessageDao


    companion object {
        @Volatile private var instance: MessageDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }
        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,MessageDatabase::class.java,"messagedatabase"
        ).build()
    }




}