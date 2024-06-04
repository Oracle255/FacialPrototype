package com.capstone.prettyU.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.prettyU.BackEnd.Utilities.DevTestConfig.Companion.loremWithLength
import com.capstone.prettyU.BackEnd.data.Item
import com.capstone.prettyU.R
import com.capstone.prettyU.View.adapter.ItemData.TreatmentData
import com.capstone.prettyU.View.adapter.RvAdapterHorizontal
import com.capstone.prettyU.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var rvItem: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvItem = binding.rvItem

        rvItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // Set horizontal orientation

        // TODO: (SET ONCLICK LISTENER) & Mock data json api
        val dataList = listOf(
            TreatmentData(R.drawable.ic_placeholder, "wdqwdqwdqwdqwdqw", "${loremWithLength(12)}"),
            TreatmentData(R.drawable.ic_placeholder, "${loremWithLength(1)}", "${loremWithLength(12)}"),
            TreatmentData(R.drawable.ic_placeholder, "${loremWithLength(1)}", "${loremWithLength(12)}")
        )

        val adapter = RvAdapterHorizontal(dataList)
        rvItem.adapter = adapter
    }

    // TODO: set animasi
    private fun playAnimation() {

    }
}