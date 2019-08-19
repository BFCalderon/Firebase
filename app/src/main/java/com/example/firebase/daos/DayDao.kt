package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.DayEntity

@Dao
interface DayDao {
    @Insert
    fun insert(dayInformation: DayEntity)

    @Update
    fun update(vararg dayInformation: DayEntity)

    @Delete
    fun delete(vararg dayInformation: DayEntity)

    @Query("SELECT * FROM " + DayEntity.TABLE_NAME +" ORDER BY DAY_COLUM")
    fun getDays(): LiveData<List<DayEntity>>
}