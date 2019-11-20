package com.example.raseedytask.adslist.view.adapter

import android.view.View
import com.example.raseedytask.R
import com.example.raseedytask.adslist.model.AdsModel
import com.example.raseedytask.helpers.base.BaseAdapter
import com.example.raseedytask.helpers.listeners.CustomItemClickListener

class AdsAdapter(private val list: List<AdsModel>, private val listener: CustomItemClickListener<AdsModel>) :
    BaseAdapter() {


    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.recycler_view_item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getObjForPosition(position: Int): Any {
        return list[position]
    }

    fun onItemClicked(v: View, item: AdsModel) {
        listener.onItemClick(v, item)
    }
}