package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.YearEntity

@Dao
interface YearDao {
    @Insert
    fun insert(yearInformation: YearEntity)

    @Update
    fun update(vararg yearInformation: YearEntity)

    @Delete
    fun delete(vararg yearInformation: YearEntity)

    @Query("SELECT * FROM " + YearEntity.TABLE_NAME +" ORDER BY " + YearEntity.YEAR)
    fun getYears(): LiveData<List<YearEntity>>
}