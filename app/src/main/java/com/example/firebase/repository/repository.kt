package com.example.firebase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebase.daos.TreeInformationDao
import com.example.firebase.entities.TreeInformationEntity
import com.example.firebase.roomdatabase.TreeInformationRoom

class TreeInformationRepository(application: Application) {
    private val treeInformationDao: TreeInformationDao? = TreeInformationRoom.getInstance(application)?.TreeInformationDao()

    fun insert(treeInformation: TreeInformationEntity) {
        if (treeInformationDao != null) InsertAsyncTask(treeInformationDao).execute(treeInformation)
    }

    fun getTreeInformation(): LiveData<List<TreeInformationEntity>> {
        return treeInformationDao?.getOrderedAgenda() ?: MutableLiveData<List<TreeInformationEntity>>()
    }

    private class InsertAsyncTask(private val treeInformationDao: TreeInformationDao) :
        AsyncTask<TreeInformationEntity, Void, Void>() {
        override fun doInBackground(vararg treeInformations: TreeInformationEntity?): Void? {
            for (treeInformation in treeInformations) {
                if (treeInformation != null) treeInformationDao.insert(treeInformation)
            }
            return null
        }
    }
}