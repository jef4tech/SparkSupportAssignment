package com.jef4tech.sparksupportassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object Extensions {
    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
    fun loadImagefromUrl(context: Context, imgView: ImageView, url:String){
        Glide.with(context).load(url).apply( RequestOptions().override(100, 100)).fitCenter().into(imgView)
    }

}