package com.mhandharbeni.firstkotlin

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter(private val customerList:ArrayList<Customer>, val clickListener : (Customer) -> Unit): RecyclerView.Adapter<CustomAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.txtName.text = customerList[position].name
        holder.txtTitle.text = customerList[position].email
        holder.cvParent.setOnClickListener {
            clickListener(customerList[position])
        }
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cvParent = itemView.findViewById<CardView>(R.id.cvParent)!!
        val txtName = itemView.findViewById<TextView>(R.id.txtName)!!
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)!!
    }

    fun updateData(customerLists: ArrayList<Customer>){
        customerList.clear()
        customerList.addAll(customerLists)
        notifyDataSetChanged()
    }
}