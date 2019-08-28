package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.YearEntity
import com.example.firebase.valueobjects.DateInformationVO

object YearDTO : GenericDataTransferObject<YearEntity, DateInformationVO>() {
    override fun dataToObject(entity: YearEntity): DateInformationVO {
        return DateInformationVO(
            entity.yearId,
            entity.year,
            entity.power,
            entity.efficiency
        )
    }

    override fun objectToData(objectVO: DateInformationVO): YearEntity {
        return YearEntity(
            objectVO.primaryKey,
            objectVO.date!!,
            objectVO.power!!,
            objectVO.efficiency
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<YearEntity>>) =
        getVOLiveData(entities)

    //Transforma los elementos que llegan de la base de datos como un List
    fun getInformationDate(dateInformation: YearEntity): DateInformationVO {
        return DateInformationVO(
            primaryKey = dateInformation.yearId!!,
            date = dateInformation.year,
            power = dateInformation.power,
            efficiency = dateInformation.efficiency
        )
    }
}