package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = YearEntity.TABLE_NAME)
data class YearEntity(  @PrimaryKey @ColumnInfo(name = YEAR_ID) var yearId: Int?,
                        @ColumnInfo(name = YEAR) var year: Int?,
                        @ColumnInfo(name = POWER) var power: Float?,
                        @ColumnInfo(name = EFFICIENCY) var efficiency: Float?){

    /*@PrimaryKey
    @ColumnInfo(name = YEAR_ID) var yearId: Int = 1*/

    companion object {
        const val TABLE_NAME = "YEAR"
        const val YEAR_ID = "YEAR_ID_COLUM"
        const val YEAR = "YEAR_COLUM"
        const val POWER = "POWER_COLUM"
        const val EFFICIENCY = "EFFICIENCY_COLUM"
    }
}