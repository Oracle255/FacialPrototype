package com.example.facialprototype.ProtoActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facialprototype.R
import com.example.facialprototype.databinding.ActivityDebugLandingBinding

class _DebugLandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDebugLandingBinding
    private val currentActivity = this@_DebugLandingActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebugLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnDebugSplash.setOnClickListener {
                startActivity(Intent(currentActivity, SplashActivity::class.java))
            }
            btnDebugLogin.setOnClickListener {
                startActivity(Intent(currentActivity, SplashActivity::class.java))
            }
            btnDebugRegister.setOnClickListener {
                startActivity(Intent(currentActivity, SplashActivity::class.java))
            }
        }

    }

}