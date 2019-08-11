package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.annotations.NotNull


@Entity(tableName = TreeInformationEntity.TABLE_NAME)
data class TreeInformationEntity(
    @ColumnInfo(name = "DATE") @NotNull val DATE: String,
    @ColumnInfo(name = "HOUR") @NotNull val HOUR: String,
    @ColumnInfo(name = "POWER") val POWER: Float? = null
) {
    companion object {
        const val TABLE_NAME = "TREE_INFORMATION"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "DATE_ID")
    var dateId: Int = 0
}