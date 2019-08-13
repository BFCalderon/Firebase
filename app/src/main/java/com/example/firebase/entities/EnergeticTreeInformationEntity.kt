package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = TreeInformationEntity.TABLE_NAME)
data class TreeInformationEntity( @ColumnInfo(name = DATE)
                                  var date: String?,

                                  @ColumnInfo(name = HOUR)
                                  var hour: String?,

                                  @ColumnInfo(name = POWER)
                                  var power: Float? = null){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DATE_ID) var dateId: Int = 0

    companion object {
        const val TABLE_NAME = "TREE_INFORMATION"
        const val DATE_ID = "DATE_ID"
        const val DATE = "DATE"
        const val HOUR = "HOUR"
        const val POWER = "POWER"
    }
}