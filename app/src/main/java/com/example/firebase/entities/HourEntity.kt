package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = HourEntity.TABLE_NAME)
data class HourEntity(@ColumnInfo(name = HOUR) var hour: Int?,
                      @ColumnInfo(name = POWER) var power: Float?,
                      @ColumnInfo(name = EFFICIENCY) var efficiency: Float?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HOUR_ID)
    var hourId: Int = 0

    companion object {
        const val TABLE_NAME = "HOUR"
        const val HOUR_ID = "HOUR_ID"
        const val HOUR = "HOUR"
        const val POWER = "POWER"
        const val EFFICIENCY = "EFFICIENCY"
    }
}