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

    fun getAllYearInformation() = repository.getYear()
    fun saveYearInformation(yearInformation: DateInformationVO) {
        repository.insertYear(yearInformation)
    }

    fun getAllMonthInformation() = repository.getMonth()
    fun saveMonthInformation(monthInformation: DateInformationVO) {
        repository.insertMont(monthInformation)
    }

    fun getAllDaysInformation() = repository.getDay()
    fun saveDaysInformation(dayInformation: DateInformationVO) {
        repository.insertDays(dayInformation)
    }

    fun getAllHoursInformation() = repository.getHour()
    fun saveHoursInformation(hourInformation: DateInformationVO) {
        repository.insertHours(hourInformation)
    }
}