package com.example.raseedytask.adslist.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["title", "order"])
data class AdsModel(

    @SerializedName("picture") val picture: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("order") val order: Int
)


