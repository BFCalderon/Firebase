package com.example.firebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.valueobjects.DateInformationVO
import kotlinx.android.synthetic.main.adapter_date.view.*

class TreeInformationAdapter(items: ArrayList<DateInformationVO>): RecyclerView.Adapter<TreeInformationAdapter.ViewHolder>() {

    var items: ArrayList<DateInformationVO> ?= null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.adapter_date, parent, false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.textDate.text = item?.date.toString()
        holder.textHour.text = item?.power.toString()
        holder.textPower.text = item?.efficiency.toString()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textDate: TextView = view.textDate
        var textHour: TextView = view.textHour
        var textPower: TextView = view.textPower
    }
}