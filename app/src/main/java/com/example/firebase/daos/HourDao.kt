package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.HourEntity

@Dao
interface HourDao {
    @Insert
    fun insert(hourInformation: HourEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateRow(dayInformation: HourEntity)

    @Update
    fun update(vararg hourInformation: HourEntity)

    @Delete
    fun delete(vararg hourInformation: HourEntity)

    @Query("SELECT * FROM " + HourEntity.TABLE_NAME +" ORDER BY HOUR_COLUM")
    fun getHours(): LiveData<List<HourEntity>>
}