package com.example.firebase.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firebase.daos.TreeInformationDao
import com.example.firebase.entities.TreeInformationEntity

@Database(entities = [TreeInformationEntity::class], version = 1)
abstract class TreeInformationRoom : RoomDatabase() {
    abstract fun TreeInformationDao(): TreeInformationDao

    companion object {
        private const val DATABASE_NAME = "score_database"
        @Volatile
        private var INSTANCE: TreeInformationRoom? = null

        fun getInstance(context: Context): TreeInformationRoom? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TreeInformationRoom::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}