package com.capstone.prettyU.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.prettyU.databinding.ActivityDebugLandingBinding

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
                moveTo(SplashActivity::class.java)
            }

            btnDebugLogin.isEnabled = true
            btnDebugLogin.setOnClickListener {
                moveTo(LoginActivity::class.java)
            }

            btnDebugRegister.isEnabled = true
            btnDebugRegister.setOnClickListener {
                moveTo(RegisterActivity::class.java)
            }

            btnDebugMain.isEnabled = true
            btnDebugMain.setOnClickListener {
                moveTo(MainActivity::class.java)
            }


        }
    }

    // Simplifikasi
    private fun moveTo(nextActivity: Class<*>) {
        startActivity(Intent(currentActivity, nextActivity))
    }
}