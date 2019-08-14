package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TreeInformationEntity.TABLE_NAME)
data class DayTreeInformationEntity(
                                    @ColumnInfo(name = DAY) var day: Int?,
                                    @ColumnInfo(name = POWER) var power: Int?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DAY_ID)
    var dayId: Int = 0

    companion object {
        const val TABLE_NAME = "DAY_TREE_INFORMATION"
        const val DAY_ID = "DAY_ID"
        const val DAY = "DAY"
        const val POWER = "POWER"
    }
}