package com.example.facialprototype.ProtoActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facialprototype.R
import com.example.facialprototype.databinding.ActivityProtoLandingBinding

class ProtoLanding : AppCompatActivity() {
    private lateinit var binding: ActivityProtoLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProtoLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}