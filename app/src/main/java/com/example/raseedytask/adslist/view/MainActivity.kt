package com.example.raseedytask.adslist.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.raseedytask.App
import com.example.raseedytask.R
import com.example.raseedytask.adslist.model.AdsModel
import com.example.raseedytask.adslist.view.adapter.AdsAdapter
import com.example.raseedytask.adslist.viewmodel.AdsViewModel
import com.example.raseedytask.databinding.ActivityMainBinding
import com.example.raseedytask.helpers.listeners.CustomItemClickListener


class MainActivity : AppCompatActivity(), CustomItemClickListener<AdsModel> {
    override fun onItemClick(v: View?, obj: AdsModel?) {
        val url = obj?.url
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private lateinit var viewModel: AdsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        App.getAppComponent().inject(this)
        viewModel = ViewModelProviders.of(this).get(AdsViewModel::class.java)
        binding.lifecycleOwner=this
        binding.viewModel = viewModel
        viewModel.getAds()
        viewModel.adsResponse.observe(this, Observer {
            Log.d("dd", "${it.size}")
            val adsAdapter = AdsAdapter(it, this)
            binding.recyclerView.adapter = adsAdapter
        })
    }
}
