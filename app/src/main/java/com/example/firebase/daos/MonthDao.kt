package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.MonthEntity

@Dao
interface MonthDao {
    @Insert
    fun insert(mounthInformation: MonthEntity)

    @Update
    fun update(vararg mounthInformation: MonthEntity)

    @Delete
    fun delete(vararg mounthInformation: MonthEntity)

    @Query("SELECT * FROM " + MonthEntity.TABLE_NAME +" ORDER BY MONTH")
    fun getMonths(): LiveData<List<MonthEntity>>
}