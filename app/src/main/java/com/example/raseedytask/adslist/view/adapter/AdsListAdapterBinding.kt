package com.example.raseedytask.adslist.view.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.raseedytask.adslist.model.AdsModel
import com.example.raseedytask.helpers.listeners.CustomItemClickListener

class AdsListAdapterBinding {

    @BindingAdapter("adsList", "onItemClicked")
    fun setOnItemClickedListener(view: RecyclerView,
                                 list: List<AdsModel>,
                                 listener: CustomItemClickListener<AdsModel>) {
        val adapter = AdsAdapter(list, listener)
        view.adapter = adapter
    }

}