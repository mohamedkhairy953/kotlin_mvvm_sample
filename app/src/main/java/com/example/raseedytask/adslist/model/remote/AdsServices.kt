package com.example.raseedytask.adslist.model.remote

import androidx.lifecycle.LiveData
import com.example.raseedytask.helpers.livedata.ApiResponse
import com.example.raseedytask.adslist.model.AdsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsServices {
    @GET("get_ad/")
    fun fetchAds(@Query("solo") solo: Boolean): LiveData<ApiResponse<List<AdsModel>>>

}