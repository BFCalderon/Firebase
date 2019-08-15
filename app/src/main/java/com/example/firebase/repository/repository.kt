package com.example.firebase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.firebase.daos.TreeInformationDao
import com.example.firebase.datatransferobjects.dateInformationDTO.dateInformationDTO
import com.example.firebase.entities.TreeInformationEntity
import com.example.firebase.roomdatabase.TreeInformationRoom
import com.example.firebase.valueobjects.DateInformationVO

class TreeInformationRepository(application: Application) {
    private val treeInformationDao: TreeInformationDao? = TreeInformationRoom.getInstance(application)?.treeInformationDao()

    fun insert(treeInformation: DateInformationVO) {
        if (treeInformationDao != null) AInsertAsyncTask(treeInformationDao).execute(dateInformationDTO.objectToData(treeInformation))
    }

    fun getTreeInformation(): LiveData<List<DateInformationVO>> {
        return dateInformationDTO.getInformationDate(treeInformationDao?.getOrderedAgenda()!!)
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
}