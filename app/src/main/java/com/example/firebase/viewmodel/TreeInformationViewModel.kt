package com.example.firebase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firebase.entities.TreeInformationEntity
import com.example.firebase.repository.TreeInformationRepository

class TreeInformationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TreeInformationRepository(application)
    val treeInformation = repository.getTreeInformation()

    fun SaveTreeInformation(treeInformation: TreeInformationEntity) {
        repository.insert(treeInformation)
    }
}