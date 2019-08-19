package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = HourEntity.TABLE_NAME)
data class HourEntity(@ColumnInfo(name = FOREING_KEY) var foreingKey: Int?,
                      @ColumnInfo(name = FOREING_KEY_1) var foreingKey1: Int?,
                      @ColumnInfo(name = FOREING_KEY_2) var foreingKey2: Int?,
                      @ColumnInfo(name = HOUR) var hour: Int?,
                      @ColumnInfo(name = POWER) var power: Float?,
                      @ColumnInfo(name = EFFICIENCY) var efficiency: Float?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HOUR_ID)
    var hourId: Int = 0

    companion object {
        const val TABLE_NAME = "HOUR"
        const val HOUR_ID = "HOUR_ID_COLUM"
        const val FOREING_KEY = "FEREING_KEY_COLUM"
        const val FOREING_KEY_1 = "FEREING_KEY_1_COLUM"
        const val FOREING_KEY_2 = "FEREING_KEY_2_COLUM"
        const val HOUR = "HOUR_COLUM"
        const val POWER = "POWER_COLUM"
        const val EFFICIENCY = "EFFICIENCY_COLUM"
    }
}