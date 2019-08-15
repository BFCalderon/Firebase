package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.DayEntity
import com.example.firebase.valueobjects.DateInformationVO

object DayDTO: GenericDataTransferObject<DayEntity, DateInformationVO>() {

    override fun dataToObject(entity: DayEntity): DateInformationVO {
        return DateInformationVO(
            entity.day,
            entity.power,
            entity.efficiency)
    }

    override fun objectToData(objectVO: DateInformationVO): DayEntity {
        return DayEntity(
            objectVO.date,
            objectVO.power!!,
            objectVO.efficiency
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<DayEntity>>) = getVOLiveData(entities)

    fun getInformationDate(dateInformation: DayEntity): DateInformationVO {
        return DateInformationVO(
            date = dateInformation.day,
            power = dateInformation.power,
            efficiency = dateInformation.efficiency
        )
    }
}