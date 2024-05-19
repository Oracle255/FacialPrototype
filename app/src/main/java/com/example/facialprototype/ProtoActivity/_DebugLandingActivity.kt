package com.example.facialprototype.ProtoActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facialprototype.R
import com.example.facialprototype.databinding.ActivityDebugLandingBinding

class _DebugLandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDebugLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebugLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}