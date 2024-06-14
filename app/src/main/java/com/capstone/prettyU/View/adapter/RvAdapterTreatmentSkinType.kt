package com.capstone.prettyU.View.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.prettyU.R
import com.capstone.prettyU.View.adapter.ItemData.TreatmentData

class RvAdapterTreatmentSkinType(private val items: List<TreatmentData>) :
    RecyclerView.Adapter<RvAdapterTreatmentSkinType.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_treatment_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //TODO SET INTENT KIRIM DATA KE activity_treatment_item
        }

    }
}