package com.example.firebase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firebase.repository.TreeInformationRepository
import com.example.firebase.valueobjects.DateInformationVO

class TreeInformationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TreeInformationRepository(application)

    fun getAllDateInformation() = repository.getTreeInformation()
    fun saveTreeInformation(treeInformation: DateInformationVO) {
        repository.insert(treeInformation)
    }

    fun getAllYearInformation() = repository.getYears()
    fun saveYearInformation(yearInformation: DateInformationVO) {
        repository.insertYear(yearInformation)
    }
    fun deleteYearInformation(year: Int) {
        repository.deleteYear(year)
    }
    fun cleanTableYears() {
        repository.cleanTableYears()
    }

    fun getAllMonthInformation(/*year: Int*/) = repository.getMonth()
    fun getMonthsPerYear(year: Int) = repository.getMonths(year)
    fun saveMonthInformation(monthInformation: DateInformationVO) {
        repository.insertMont(monthInformation)
    }
    fun cleanTableMonths() {
        repository.cleanTableMonths()
    }

    fun getDaysByMonths(year: Int, month: Int) = repository.getDaysByMonths(year, month)
    fun getAllDaysInformation() = repository.getDay()
    fun saveDaysInformation(dayInformation: DateInformationVO) {
        repository.insertDays(dayInformation)
    }
    fun cleanTableDays(){
        repository.cleanTableDays()
    }

    fun getSpecificHours(year: Int, month: Int, day: Int) = repository.getSpecificHours(year, month, day)
    fun getAllHoursInformation() = repository.getHour()
    fun saveHoursInformation(hourInformation: DateInformationVO) {
        repository.insertHours(hourInformation)
    }
    fun cleanTableHours(){
        repository.cleanTableHours()
    }
}