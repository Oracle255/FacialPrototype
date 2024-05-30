package com.capstone.prettyU.View.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.prettyU.R
import com.capstone.prettyU.View.adapter.ItemData.TreatmentData

class RvAdapterHorizontal(private val items: List<TreatmentData>) : RecyclerView.Adapter<RvAdapterHorizontal.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_treatment_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.iv_treatment.setImageResource(item.image)
        holder.tv_treatment_name.text = item.title
        holder.tv_treatment_desc.text = item.desc
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_treatment: ImageView = itemView.findViewById(R.id.iv_treatment)
        val tv_treatment_name: TextView = itemView.findViewById(R.id.tv_treatment_name)
        val tv_treatment_desc: TextView = itemView.findViewById(R.id.tv_treatment_desc)
    }
}