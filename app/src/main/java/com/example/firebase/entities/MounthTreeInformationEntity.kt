package com.example.firebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TreeInformationEntity.TABLE_NAME)
data class MountTreeInformationEntity( @ColumnInfo(name = MONTH)
                                      var month: Int?){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MONTH_ID) var monthId: Int = 0

    companion object {
        const val TABLE_NAME = "MONTH_TREE_INFORMATION"
        const val MONTH_ID = "MONTH_ID"
        const val MONTH = "MONTH"
    }
}