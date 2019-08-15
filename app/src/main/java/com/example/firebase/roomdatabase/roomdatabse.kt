package com.example.firebase.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firebase.daos.*
import com.example.firebase.entities.*

@Database(entities = [
    TreeInformationEntity::class,
    DayEntity::class,
    HourEntity::class,
    YearEntity::class,
    MonthEntity::class
], version = 1)
abstract class TreeInformationRoom : RoomDatabase() {

    abstract fun treeInformationDao(): TreeInformationDao
    abstract fun yearDao(): YearDao
    abstract fun monthDao(): MonthDao
    abstract fun dayDao(): DayDao
    abstract fun hourDao(): HourDao


    companion object {
        private const val DATABASE_NAME = "ARBOL_ENERGETICO"
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

