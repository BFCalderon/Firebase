package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TreeInformationEntity.TABLE_NAME)
data class HourTreeInformationEntity( @ColumnInfo(name = HOUR) var hour: Int?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HOUR_ID)
    var hourId: Int = 0

    companion object {
        const val TABLE_NAME = "HOUR_TREE_INFORMATION"
        const val HOUR_ID = "HOUR_ID"
        const val HOUR = "HOUR"
    }
}