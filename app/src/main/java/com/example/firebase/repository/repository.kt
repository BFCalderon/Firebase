package com.example.firebase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.firebase.daos.*
import com.example.firebase.datatransferobjects.dateInformationDTO.*
import com.example.firebase.entities.*
import com.example.firebase.roomdatabase.TreeInformationRoom
import com.example.firebase.valueobjects.DateInformationVO

class TreeInformationRepository(application: Application) {
    private val treeInformationDao: TreeInformationDao? = TreeInformationRoom.getInstance(application)?.treeInformationDao()

    private val yearDao: YearDao? = TreeInformationRoom.getInstance(application)?.yearDao()
    private val monthDao: MonthDao? = TreeInformationRoom.getInstance(application)?.monthDao()
    private val dayDao: DayDao? = TreeInformationRoom.getInstance(application)?.dayDao()
    private val hourDao: HourDao? = TreeInformationRoom.getInstance(application)?.hourDao()

    //Generica funcional START
    fun getTreeInformation(): LiveData<List<DateInformationVO>> {
        return DateInformationDTO.getInformationDate(treeInformationDao?.getOrderedAgenda()!!)
    }
    fun insert(treeInformation: DateInformationVO) {
        if (treeInformationDao != null) AInsertAsyncTask(treeInformationDao).execute(DateInformationDTO.objectToData(treeInformation))
    }
    private class AInsertAsyncTask(private val treeInformationDao: TreeInformationDao) :
        AsyncTask<TreeInformationEntity, Void, Void>() {
        override fun doInBackground(vararg treeInformations: TreeInformationEntity?): Void? {
            for (treeInformation in treeInformations) {
                if (treeInformation != null) treeInformationDao.insert(treeInformation)
            }
            return null
        }
    }
    //Generica funcional END

    //YEAR START
    fun getYear(): LiveData<List<DateInformationVO>> {
        return YearDTO.getInformationDate(yearDao!!.getYears())
    }
    fun insertYear(yearformation: DateInformationVO) {
        if (yearDao != null) AInsertYearAsyncTask(yearDao).execute(YearDTO.objectToData(yearformation))
    }
    private class AInsertYearAsyncTask(private val yearDao: YearDao) :
        AsyncTask<YearEntity, Void, Void>() {
        override fun doInBackground(vararg yearformations: YearEntity?): Void? {
            for (yearformation in yearformations) {
                if (yearformation != null) yearDao.insert(yearformation)
            }
            return null
        }
    }
    //YEAR END

    //MONTH START
    fun getMonth(): LiveData<List<DateInformationVO>> {
        return MonthDTO.getInformationDate(monthDao!!.getMonths())
    }
    fun insertMont(monthInformation: DateInformationVO) {
        if (monthDao != null) AInsertMonthAsyncTask(monthDao).execute(MonthDTO.objectToData(monthInformation))
    }
    private class AInsertMonthAsyncTask(private val monthDao: MonthDao) :
        AsyncTask<MonthEntity, Void, Void>() {
        override fun doInBackground(vararg monthInformations: MonthEntity?): Void? {
            for (monthInformation in monthInformations) {
                if (monthInformation != null) monthDao.insert(monthInformation)
            }
            return null
        }
    }
    //MONTH END

    //DAY START
    fun getDay(): LiveData<List<DateInformationVO>> {
        return DayDTO.getInformationDate(dayDao!!.getDays())
    }
    fun insertDays(dayInformation: DateInformationVO) {
        if (dayDao != null) AInsertDayAsyncTask(dayDao).execute(DayDTO.objectToData(dayInformation))
    }
    private class AInsertDayAsyncTask(private val dayDao: DayDao) :
        AsyncTask<DayEntity, Void, Void>() {
        override fun doInBackground(vararg dayInformations: DayEntity?): Void? {
            for (dayInformation in dayInformations) {
                if (dayInformation != null) dayDao.insert(dayInformation)
            }
            return null
        }
    }
    //DAY END

    //HOUR START
    fun getHour(): LiveData<List<DateInformationVO>> {
        return HourDTO.getInformationDate(hourDao!!.getHours())
    }
    fun insertHours(hourInformation: DateInformationVO) {
        if (hourDao != null) AInsertHourAsyncTask(hourDao).execute(HourDTO.objectToData(hourInformation))
    }
    private class AInsertHourAsyncTask(private val hourDao: HourDao) :
        AsyncTask<HourEntity, Void, Void>() {
        override fun doInBackground(vararg hourInformations: HourEntity?): Void? {
            for (dayInformation in hourInformations) {
                if (dayInformation != null) hourDao.insert(dayInformation)
            }
            return null
        }
    }
    //HOUR END
}