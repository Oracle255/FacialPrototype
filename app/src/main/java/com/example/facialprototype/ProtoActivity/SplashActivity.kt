package com.example.facialprototype.ProtoActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facialprototype.R
import com.example.facialprototype.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

        }

    }
}