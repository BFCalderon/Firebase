package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.MonthEntity
import com.example.firebase.entities.YearEntity
import com.example.firebase.entities.business.monthInformationJoin

@Dao
interface MonthDao {
    @Insert
    fun insert(mounthInformation: MonthEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateRow(dayInformation: MonthEntity)

    @Update
    fun update(vararg mounthInformation: MonthEntity)

    @Delete
    fun delete(vararg mounthInformation: MonthEntity)

    @Query(
        "SELECT * " +
                " FROM " + MonthEntity.TABLE_NAME +
                " INNER JOIN " + YearEntity.TABLE_NAME +
                " ON " + YearEntity.YEAR +
                " WHERE " + YearEntity.YEAR_ID + " = " + MonthEntity.FOREING_KEY + " AND " + YearEntity.YEAR + "=:year"
    )
    fun getMonths(year: Int): LiveData<List<MonthEntity>>

    @Query("SELECT * FROM " + MonthEntity.TABLE_NAME +" ORDER BY MONTH_COLUM")
    fun getMonth(): LiveData<List<MonthEntity>>
}