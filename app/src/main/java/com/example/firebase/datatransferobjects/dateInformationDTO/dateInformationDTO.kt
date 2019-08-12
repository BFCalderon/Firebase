package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.TreeInformationEntity
import com.example.firebase.valueobjects.DateInformationVO

object dateInformationDTO : GenericDataTransferObject<TreeInformationEntity, DateInformationVO>() {
    override fun dataToObject(entity: TreeInformationEntity): DateInformationVO {
        return DateInformationVO(
            entity.date,
            entity.hour,
            entity.power)
    }

    override fun objectToData(objectVO: DateInformationVO): TreeInformationEntity {
        return TreeInformationEntity(
            objectVO.date!!,
            objectVO.hour!!,
            objectVO.power
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<TreeInformationEntity>>) =
        getVOLiveData(entities)

    fun getInformationDate(dateInformation: TreeInformationEntity): DateInformationVO{
        return DateInformationVO(
            date = dateInformation.date,
            hour = dateInformation.hour,
            power = dateInformation.power
        )
    }
}