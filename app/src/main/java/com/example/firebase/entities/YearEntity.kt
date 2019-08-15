package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = YearEntity.TABLE_NAME)
data class YearEntity(
                        @ColumnInfo(name = YEAR) var year: Int?,
                        @ColumnInfo(name = POWER) var power: Float?,
                        @ColumnInfo(name = EFFICIENCY) var efficiency: Float?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = YEAR_ID) var yearId: Int = 0

    companion object {
        const val TABLE_NAME = "YEAR"
        const val YEAR_ID = "YEAR_ID"
        const val YEAR = "YEAR"
        const val POWER = "POWER"
        const val EFFICIENCY = "EFFICIENCY"
    }
}