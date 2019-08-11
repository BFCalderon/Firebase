package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.TreeInformationEntity
import com.example.firebase.valueobjects.dateInformationVO
import java.lang.reflect.Array

object dateInformationDTO : GenericDataTransferObject<TreeInformationEntity, dateInformationVO>() {
    override fun dataToObject(entity: TreeInformationEntity): dateInformationVO {
        return dateInformationVO(
            entity.DATE,
            entity.HOUR,
            entity.POWER)
    }

    override fun objectToData(objectVO: dateInformationVO): TreeInformationEntity {
        return TreeInformationEntity(
            objectVO.date!!,
            objectVO.hour!!,
            objectVO.power
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<TreeInformationEntity>>) =
        getVOLiveData(entities)

    fun getInformationDate(dateInformation: TreeInformationEntity): dateInformationVO{
        return dateInformationVO(
            date = dateInformation.DATE,
            hour = dateInformation.HOUR,
            power = dateInformation.POWER
        )
    }
}