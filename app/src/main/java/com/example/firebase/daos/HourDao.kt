package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.DayEntity
import com.example.firebase.entities.HourEntity
import com.example.firebase.entities.MonthEntity
import com.example.firebase.entities.YearEntity

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

    @Query(
        "SELECT * " +
                " FROM " + HourEntity.TABLE_NAME +
                " WHERE " +

                " (SELECT " + MonthEntity.MONTH +
                " FROM " + MonthEntity.TABLE_NAME+
                " WHERE " + MonthEntity.MONTH+" =:month)" +
                " = " + HourEntity.FOREING_KEY_1 +

                " AND (SELECT " + DayEntity.DAY +
                " FROM " + DayEntity.TABLE_NAME+
                " WHERE " + DayEntity.DAY+" =:day)" +
                " = " + DayEntity.FOREING_KEY +

                " AND " + HourEntity.FOREING_KEY_2 +
                " = (SELECT " + YearEntity.YEAR_ID +
                " FROM " + YearEntity.TABLE_NAME+
                " WHERE " + YearEntity.YEAR+" =:year)"
    )
    fun getSpecificHours(year: Int, month: Int, day: Int): LiveData<List<HourEntity>>

    @Query("SELECT * FROM " + HourEntity.TABLE_NAME +" ORDER BY HOUR_COLUM")
    fun getHours(): LiveData<List<HourEntity>>
}