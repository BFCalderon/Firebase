package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MonthEntity.TABLE_NAME)
data class MonthEntity(@PrimaryKey @ColumnInfo(name = MONTH_ID) var monthId: Int ?= null,
                       @ColumnInfo(name = FOREING_KEY) var foreingKey: Int? = null,
                       @ColumnInfo(name = MONTH) var monthEntity: Int? = null,
                       @ColumnInfo(name = POWER) var power: Float? = null,
                       @ColumnInfo(name = EFFICIENCY) var efficiency: Float? = null){

    /*@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MONTH_ID) var monthId: Int = 0*/

    companion object {
        const val TABLE_NAME = "MONTH"
        const val MONTH_ID = "MONTH_ID_COLUM"
        const val FOREING_KEY = "FOREING_KEY_COLUM"
        const val MONTH = "MONTH_COLUM"
        const val POWER = "POWER_COLUM"
        const val EFFICIENCY = "EFFICIENCY_COLUM"
    }
}