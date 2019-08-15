package com.example.firebase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebase.entities.TreeInformationEntity

@Dao
interface TreeInformationDao {
    @Insert
    fun insert(treeInformation: TreeInformationEntity)

    @Update
    fun update(vararg treeInformation: TreeInformationEntity)

    @Delete
    fun delete(vararg treeInformation: TreeInformationEntity)

    @Query("SELECT * FROM " + TreeInformationEntity.TABLE_NAME + " ORDER BY DATE")
    fun getOrderedAgenda(): LiveData<List<TreeInformationEntity>>
}