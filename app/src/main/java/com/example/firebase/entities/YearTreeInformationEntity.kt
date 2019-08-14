package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TreeInformationEntity.TABLE_NAME)
data class YearTreeInformationEntity( @ColumnInfo(name = YEAR) var year: Int?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = YEAR_ID) var yearId: Int = 0

    companion object {
        const val TABLE_NAME = "YEAR_TREE_INFORMATION"
        const val YEAR_ID = "YEAR_ID"
        const val YEAR = "YEAR"
    }
}