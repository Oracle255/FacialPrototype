package com.capstone.prettyU.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.prettyU.databinding.ActivityCosmeticListBinding

class CosmeticListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCosmeticListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCosmeticListBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}