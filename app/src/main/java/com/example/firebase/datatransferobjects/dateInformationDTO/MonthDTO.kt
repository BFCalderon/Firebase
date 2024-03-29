package com.example.firebase.datatransferobjects.dateInformationDTO

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.firebase.datatransferobjects.GenericDataTransferObject
import com.example.firebase.entities.MonthEntity
import com.example.firebase.entities.business.monthInformationJoin
import com.example.firebase.valueobjects.DateInformationVO

object MonthDTO: GenericDataTransferObject<MonthEntity, DateInformationVO>() {

    override fun dataToObject(entity: MonthEntity): DateInformationVO {
        return DateInformationVO(
            entity.monthId!!,
            entity.monthEntity,
            entity.power,
            entity.efficiency)
    }

    override fun objectToData(objectVO: DateInformationVO): MonthEntity {
        return MonthEntity(
            objectVO.primaryKey,
            objectVO.foreingKey,
            objectVO.date,
            objectVO.power!!,
            objectVO.efficiency
        )
    }

    //Transforma los elementos que llegan de la base de datos como un LiveData
    fun getInformationDate(entities: LiveData<List<MonthEntity>>) = getVOLiveData(entities)

    fun getMonths(dateInformation: LiveData<List<MonthEntity>>) = getVOLiveData(dateInformation)//: ArrayList<DateInformationVO> {
    /*val years: ArrayList<DateInformationVO> = ArrayList()
        dateInformation.forEach {
            years.add(DateInformationVO(it.years))
        }
        return years
    }*/
    fun getInformationDate(dateInformation: MonthEntity): DateInformationVO {
        return DateInformationVO(
            primaryKey = dateInformation.monthId!!,
            date = dateInformation.monthId,
            power = dateInformation.power,
            efficiency = dateInformation.efficiency
        )
    }
}