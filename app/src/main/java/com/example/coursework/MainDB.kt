package com.example.coursework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [parking::class, Cars::class, Rta::class, Contracts::class, Users::class], version = 1)
abstract class MainDB : RoomDatabase() {

    abstract fun getDao() : Dao
    companion object{
        fun getDB(context: Context): MainDB{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "test7.db"
            ).build()
        }
    }
}