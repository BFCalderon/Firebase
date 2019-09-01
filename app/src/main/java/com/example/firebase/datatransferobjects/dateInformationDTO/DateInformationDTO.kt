package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.TreeInformationEntity
import com.example.firebase.valueobjects.DateInformationVO

object DateInformationDTO : GenericDataTransferObject<TreeInformationEntity, DateInformationVO>() {
    override fun dataToObject(entity: TreeInformationEntity): DateInformationVO {
        return DateInformationVO(
            entity.dateId,
            entity.date,
            entity.power,
            entity.efficiency)
    }

    override fun objectToData(objectVO: DateInformationVO): TreeInformationEntity {
        return TreeInformationEntity(
            objectVO.date!!,
            objectVO.power!!,
            objectVO.efficiency
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<TreeInformationEntity>>) =
        getVOLiveData(entities)

    //Transforma los elementos que llegan de la base de datos como un List
    fun getInformationDate(dateInformation: TreeInformationEntity): DateInformationVO{
        return DateInformationVO(
            primaryKey = dateInformation.dateId,
            date = dateInformation.date,
            power = dateInformation.power,
            efficiency = dateInformation.efficiency
        )
    }
}