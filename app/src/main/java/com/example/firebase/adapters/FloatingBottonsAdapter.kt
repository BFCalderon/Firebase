package com.example.firebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R

/*
class FloatingBottonsAdapter(items:ArrayList<Botones>, var clickListener: click): RecyclerView.Adapter<FloatingBottonsAdapter.ViewHolder>(){

    var items: ArrayList<Botones>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
    }

    override fun onBindViewHolder(p0: FloatingBottonsAdapter.ViewHolder, p1: Int) {
        val item = items?.get(p1)
        p0.icono?.setImageResource(item?.icono!!)
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewTipe: Int): FloatingBottonsAdapter.ViewHolder {
        val vista = LayoutInflater.from(p0?.context).inflate(R.layout.layout_buttons, p0, false)
        viewHolder = ViewHolder(vista, clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    class ViewHolder(vista: View, listener: click): RecyclerView.ViewHolder(vista), View.OnClickListener{

        var vista = vista
        var icono: ImageView? = null
        var listener: click? = null

        init {
            icono = vista.icon_button
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }
    }
}*/
