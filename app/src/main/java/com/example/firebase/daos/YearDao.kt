package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.YearEntity

@Dao
interface YearDao {
    @Insert
    fun insert(yearInformation: YearEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateRow(dayInformation: YearEntity)

    @Update
    fun update(vararg yearInformation: YearEntity)

    @Query(
        "DELETE FROM " + YearEntity.TABLE_NAME + " WHERE " + YearEntity.YEAR + " =:year"
    )
    fun delete(vararg year: Int)

    @Query(
        "DELETE FROM " + YearEntity.TABLE_NAME)
    fun cleanTableYear()

    @Query("SELECT * FROM " + YearEntity.TABLE_NAME +" ORDER BY " + YearEntity.YEAR)
    fun getYears(): LiveData<List<YearEntity>>
}