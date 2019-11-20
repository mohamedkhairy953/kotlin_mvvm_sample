package com.example.raseedytask.adslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.apptcom.hawel.helpers.base.BaseViewModel
import com.example.raseedytask.App
import com.example.raseedytask.adslist.model.AdsModel
import com.example.raseedytask.adslist.model.AdsRepo
import com.example.raseedytask.helpers.livedata.Resource
import javax.inject.Inject


class AdsViewModel : BaseViewModel {
    @Inject
    lateinit var repo: AdsRepo
    val adsResponse: MutableLiveData<List<AdsModel>> = MutableLiveData()
    private lateinit var adsLiveData: LiveData<Resource<List<AdsModel>>>
    private var adsLiveDataObserver: Observer<Resource<List<AdsModel>>>

    constructor() {
       App.getAppComponent().inject(this)
        adsLiveDataObserver = getAdsObserver()
    }

    private fun getAdsObserver(): Observer<Resource<List<AdsModel>>> = Observer {
        when (it.status) {
            Resource.Status.LOADING -> {
                dataLoading.value = true
            }
            Resource.Status.SUCCESS -> {
                dataLoading.value = false
                if (it.data != null) {
                    adsResponse.value = it.data?.sortedBy { it.order }
                    showNoNetworkScreenEvent.setValue(false)
                } else {
                    errorMessageEvent.value = "network error"
                    showNoNetworkScreenEvent.setValue(true)
                }
            }
            Resource.Status.ERROR -> {
                errorMessageEvent.value = it.message
                dataLoading.value = false
                showNoNetworkScreenEvent.value = true
                errorMessageEvent.setValue(it.message)
            }
        }
    }

    fun getAds() {
        adsLiveData = repo.getAds()
        adsLiveData.observeForever(adsLiveDataObserver)
    }

    override fun onCleared() {
        super.onCleared()
        if (adsLiveData.hasActiveObservers()) {
            adsLiveData.removeObserver(adsLiveDataObserver)
        }
    }


}