package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DayEntity.TABLE_NAME)
data class DayEntity(@PrimaryKey @ColumnInfo(name = DAY_ID) var dayId: Int?,

                     @ColumnInfo(name = FOREING_KEY) var foreingKey: Int?,
                     @ColumnInfo(name = FOREING_KEY_1) var foreingKey1: Int?,
                     @ColumnInfo(name = DAY) var day: Int?,
                     @ColumnInfo(name = POWER) var power: Float?,
                     @ColumnInfo(name = EFFICIENCY) var efficiency: Float?){

    /*@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DAY_ID)
    var dayId: Int = 0*/

    companion object {
        const val TABLE_NAME = "DAY"
        const val DAY_ID = "DAY_ID_COLUM"
        const val FOREING_KEY = "FEREING_KEY_COLUM"
        const val FOREING_KEY_1 = "FEREING_KEY_1_COLUM"
        const val DAY = "DAY_COLUM"
        const val POWER = "POWER_COLUM"
        const val EFFICIENCY = "EFFICIENCY_COLUM"
    }
}