package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.HourEntity
import com.example.firebase.valueobjects.DateInformationVO

object HourDTO: GenericDataTransferObject<HourEntity, DateInformationVO>() {

    override fun dataToObject(entity: HourEntity): DateInformationVO {
        return DateInformationVO(
            entity.hourId,
            entity.hour,
            entity.power,
            entity.efficiency
        )
    }

    override fun objectToData(objectVO: DateInformationVO): HourEntity {
        return HourEntity(
            objectVO.foreingKey,
            objectVO.foreingKey1,
            objectVO.foreingKey2,
            objectVO.date,
            objectVO.power!!,
            objectVO.efficiency
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<HourEntity>>) = getVOLiveData(entities)

    fun getInformationDate(dateInformation: HourEntity): DateInformationVO {
        return DateInformationVO(
            primaryKey = dateInformation.hourId,
            date = dateInformation.hour,
            power = dateInformation.power,
            efficiency = dateInformation.efficiency
        )
    }
}