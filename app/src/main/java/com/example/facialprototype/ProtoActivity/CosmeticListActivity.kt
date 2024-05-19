package com.example.facialprototype.ProtoActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facialprototype.R
import com.example.facialprototype.databinding.ActivityCosmeticListBinding

class CosmeticListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCosmeticListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCosmeticListBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}