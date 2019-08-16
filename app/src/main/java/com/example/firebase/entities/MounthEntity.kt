package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MonthEntity.TABLE_NAME)
data class MonthEntity(@ColumnInfo(name = FOREING_KEY) var foreingKey: Int?,
                       @ColumnInfo(name = MONTH) var monthEntity: String?,
                       @ColumnInfo(name = POWER) var power: Float?,
                       @ColumnInfo(name = EFFICIENCY) var efficiency: Float?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MONTH_ID) var monthId: Int = 0

    companion object {
        const val TABLE_NAME = "MONTH"
        const val MONTH_ID = "MONTH_ID"
        const val FOREING_KEY = "FOREING_KEY"
        const val MONTH = "MONTH"
        const val POWER = "POWER"
        const val EFFICIENCY = "EFFICIENCY"
    }
}