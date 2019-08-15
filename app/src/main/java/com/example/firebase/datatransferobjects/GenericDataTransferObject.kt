package com.example.firebase.datatransferobjects

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

abstract class GenericDataTransferObject<E, O> {

    /**
     * Se convierten los datos en objetos
     * @param entity entidad del ipat
     */
    abstract fun dataToObject(entity: E): O

    /**
     * Se convierten los objectos en datos
     * @param objectVO objeto de la entidad
     */
    abstract fun objectToData(objectVO: O): E

    /**
     * Se transforma el LiveData
     */
    fun getVOLiveData(entities: LiveData<List<E>>): LiveData<List<O>> {
        return Transformations.map(entities){ it.map { entity -> dataToObject(entity)}}!!
    }

    /**
     * Se retorna lista de objetos a partir de la entidad
     */
    fun getVOArrayList(entities: List<E>?) = when (entities.isNullOrEmpty()) {
        true -> ArrayList()
        false -> entities.map { entity -> dataToObject(entity) }
    }
}