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

            btnDebugSplash.isEnabled = true
            btnDebugSplash.setOnClickListener {
                startActivity(Intent(currentActivity, SplashActivity::class.java))
            }

            btnDebugLogin.isEnabled = true
            btnDebugLogin.setOnClickListener {
                startActivity(Intent(currentActivity, LoginActivity::class.java))
            }

            btnDebugRegister.isEnabled = true
            btnDebugRegister.setOnClickListener {
                startActivity(Intent(currentActivity, RegisterActivity::class.java))
            }

        }
    }
}