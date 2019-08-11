package com.example.firebase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.firebase.daos.TreeInformationDao
import com.example.firebase.datatransferobjects.dateInformationDTO.dateInformationDTO
import com.example.firebase.entities.TreeInformationEntity
import com.example.firebase.roomdatabase.TreeInformationRoom
import com.example.firebase.valueobjects.dateInformationVO

class TreeInformationRepository(application: Application) {
    private val treeInformationDao: TreeInformationDao? = TreeInformationRoom.getInstance(application)?.TreeInformationDao()

    fun insert(treeInformation: TreeInformationEntity) {
        if (treeInformationDao != null) InsertAsyncTask(treeInformationDao).execute(treeInformation)
    }

    /*override fun doInBackground(vararg params: Int?): AccidentDescriptionVO? {
        val response = this.accidentDescriptionDao.getCurrentAccidentDescription(params[0]!!)
        return if (response != null) AccidentDescriptionDTO.getAccidentDescriptionInformation(response) else AccidentDescriptionVO()
    }*/

    /*override fun doInBackground(vararg params: PersonVO?): ArrayList<ProtectionTypeVO>? {
        val response = this.personConditionProtectionTypeDao.getProtectionType(params[0]!!.personId!!)
        return ArrayList(response.map { ProtectionTypeDTO.dataToObject(it) })
    }*/

    fun getTreeInformation(): LiveData<List<dateInformationVO>> {
        return dateInformationDTO.getInformationDate(treeInformationDao?.getOrderedAgenda()!!)
    }
    /*fun getTreeInformation(): LiveData<List<dateInformationVO>> {
        val informationFromEntity = dateInformationDTO.getInformationDate(treeInformationDao?.getOrderedAgenda()!!)
         //return LiveData(ArrayList(informationFromEntity!!.value!!.map { dateInformationDTO.getInformationDate(it) }))// dateInformationDTO.getInformationDate(informationFromEntity) else dateInformationVO()
        return Transformations.map(informationFromEntity) {it
        *//*it.map { informationDate -> dateInformationDTO.getInformationDate(informationDate) } *//*}
    }*/

    /*fun getTreeInformation(): LiveData<List<TreeInformationEntity>> {
        return treeInformationDao?.getOrderedAgenda() ?: MutableLiveData()
    }*/

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