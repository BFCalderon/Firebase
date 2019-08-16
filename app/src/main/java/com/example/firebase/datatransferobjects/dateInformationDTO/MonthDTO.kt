package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.MonthEntity
import com.example.firebase.valueobjects.DateInformationVO

object MonthDTO: GenericDataTransferObject<MonthEntity, DateInformationVO>() {

    override fun dataToObject(entity: MonthEntity): DateInformationVO {
        return DateInformationVO(
            entity.monthEntity,
            entity.power,
            entity.efficiency)
    }

    override fun objectToData(objectVO: DateInformationVO): MonthEntity {
        return MonthEntity(
            objectVO.month,
            objectVO.power!!,
            objectVO.efficiency
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<MonthEntity>>) = getVOLiveData(entities)

    fun getInformationDate(dateInformation: MonthEntity): DateInformationVO {
        return DateInformationVO(
            date = dateInformation.monthId,
            power = dateInformation.power,
            efficiency = dateInformation.efficiency
        )
    }
}