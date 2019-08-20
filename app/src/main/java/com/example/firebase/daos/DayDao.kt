package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.DayEntity
import com.example.firebase.entities.MonthEntity
import com.example.firebase.entities.YearEntity
import java.time.Month

@Dao
interface DayDao {
    @Insert
    fun insert(dayInformation: DayEntity)

    @Update
    fun update(vararg dayInformation: DayEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateRow(dayInformation: DayEntity)

    @Delete
    fun delete(vararg dayInformation: DayEntity)

    /*" (SELECT " + YearEntity.YEAR_ID +
    " FROM " + YearEntity.TABLE_NAME+
    " WHERE " + YearEntity.YEAR+" =:year)"*/

    @Query(
        "SELECT * " +
                " FROM " + DayEntity.TABLE_NAME +
                " WHERE " +
                " (SELECT " + MonthEntity.MONTH +
                " FROM " + MonthEntity.TABLE_NAME+
                " WHERE " + MonthEntity.MONTH+" =:month)" +
                " = " + DayEntity.FOREING_KEY +
                " AND " + DayEntity.FOREING_KEY_1 +
                " = (SELECT " + YearEntity.YEAR_ID +
                " FROM " + YearEntity.TABLE_NAME+
                " WHERE " + YearEntity.YEAR+" =:year)"
    )
    fun getDaysByMonths(year: Int, month: Int): LiveData<List<DayEntity>>

    @Query("SELECT * FROM " + DayEntity.TABLE_NAME +" ORDER BY DAY_COLUM")
    fun getAllDays(): LiveData<List<DayEntity>>
}