package com.example.firebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.utils.ConstantValues
import com.example.firebase.valueobjects.DateInformationVO
import kotlinx.android.synthetic.main.child_year_month.view.*

class TreeInformationAdapter(items: ArrayList<DateInformationVO>): RecyclerView.Adapter<TreeInformationAdapter.ViewHolder>() {

    var items: ArrayList<DateInformationVO> ?= null
    var viewHolder: ViewHolder? = null
    var iNotifyItemSelected: INotifyItemSelected ?= null
    private var constantValues: ConstantValues = ConstantValues()
    var isMonth = false

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.child_year_month, parent, false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.titleName.text = if(!isMonth) item?.date.toString() else constantValues!!.getStringMont(item?.date.toString().toInt())
        holder.powerValue.text = "${item?.power.toString()} W"
        holder.eficiencyValue.text = "${item?.efficiency.toString()} %"
        holder.eficiencyBar.progress = item?.efficiency!!.toInt()

        holder.parent.setOnClickListener {view->
            iNotifyItemSelected!!.sendChangeCoor(item.date.toString())
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var titleName: TextView = view.titleName
        var powerValue: TextView = view.powerText
        var eficiencyValue: TextView = view.eficiencyText
        var eficiencyBar: ProgressBar = view.eficiencyBarChlid
        var parent: LinearLayout = view.findViewById(R.id.parent)
    }

    interface INotifyItemSelected{
        fun sendChangeCoor(position: String)
    }
}