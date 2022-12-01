package com.jef4tech.sparksupportassignment.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jef4tech.sparksupportassignment.databinding.DashboardAdapterBinding
import com.jef4tech.sparksupportassignment.model.DashBoardResponse
import com.jef4tech.sparksupportassignment.model.DashBoardResponseItem
import com.jef4tech.sparksupportassignment.utils.Extensions

class DashBoardAdapter(): RecyclerView.Adapter<DashBoardAdapter.ViewHolder>() {
    var dashboardList:List<DashBoardResponseItem> = ArrayList()
    inner class ViewHolder(val custombind:DashboardAdapterBinding):RecyclerView.ViewHolder(custombind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = DashboardAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dashBoardItem = dashboardList[position]
        holder.custombind.itemId.text=dashBoardItem.id.toString()
        Extensions.loadImagefromUrl(holder.custombind.image.context,holder.custombind.image,dashBoardItem.imageLink)

    }

    override fun getItemCount(): Int {
        return dashboardList.size
    }
    fun setData(it: DashBoardResponse) {
        Log.i("TAG", "setData: $it")
        this.dashboardList = it
        notifyDataSetChanged()
    }
}