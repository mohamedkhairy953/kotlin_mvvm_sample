package com.example.raseedytask.adslist.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.raseedytask.helpers.base.BaseDao
import com.example.raseedytask.adslist.model.AdsModel
@Dao
interface AdsDao : BaseDao<AdsModel> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems(item: List<AdsModel>)

    @Query("select * from AdsModel")
    fun fetchAds(): LiveData<List<AdsModel>>

}