package com.example.raseedytask.adslist.model

import androidx.lifecycle.LiveData
import com.example.raseedytask.helpers.core.AppExecutors
import com.example.raseedytask.helpers.livedata.ApiResponse
import com.example.raseedytask.helpers.livedata.NetworkBoundResource
import com.example.raseedytask.helpers.livedata.Resource
import com.example.raseedytask.helpers.utilities.ShouldFetch
import com.example.raseedytask.adslist.model.dao.AdsDao
import com.example.raseedytask.adslist.model.remote.AdsServices
import javax.inject.Inject

class AdsRepo @Inject constructor(private val dao: AdsDao, private val services: AdsServices) {

    private val appExecutors = AppExecutors()
    fun getAds(): LiveData<Resource<List<AdsModel>>> = object :
        NetworkBoundResource<List<AdsModel>, List<AdsModel>>(appExecutors) {
        override fun saveCallResult(items: List<AdsModel>) {
            dao.insertAllItems(items)
        }

        override fun shouldFetch(data: List<AdsModel>?): Boolean {
            return ShouldFetch.networkRecommended()
        }

        override fun loadFromDb(): LiveData<List<AdsModel>> {
            return dao.fetchAds()
        }

        override fun createCall(): LiveData<ApiResponse<List<AdsModel>>> {
            return services.fetchAds(false)
        }

    }.asLiveData()


}