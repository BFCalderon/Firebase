package com.example.firebase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firebase.repository.TreeInformationRepository
import com.example.firebase.valueobjects.DateInformationVO

class TreeInformationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TreeInformationRepository(application)
    //val treeInformation = repository.getTreeInformation()

    /*fun deleDate(deleteDate: Float) {repository.deleteDate(deleteDate)}*/

    fun getAllDateInformation() = repository.getTreeInformation()

    fun saveTreeInformation(treeInformation: DateInformationVO) {
        repository.insert(treeInformation)
    }
}