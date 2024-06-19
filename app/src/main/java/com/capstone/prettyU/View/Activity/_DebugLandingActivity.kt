package com.capstone.prettyU.View.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference
import com.capstone.prettyU.databinding.ActivityDebugLandingBinding

class _DebugLandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDebugLandingBinding
    private val currentActivity = this@_DebugLandingActivity
    private lateinit var localPref: LocalPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebugLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        localPref = LocalPreference(this)
        binding.apply {

            fun showData() {
                tvDebugName.text = "NAME = ${localPref.fetchName()}"
                tvDebugToken.text = "TOKEN = ${localPref.fetchToken()}"
                tvDebugEmail.text = "EMAIL = ${localPref.fetchEmail()}"
            }
            fun removeData() {
                localPref.removeName()
                localPref.removeEmail()
                localPref.removeToken()
                localPref.removeBio()
            }
            showData()

            btnDebugClearData.setOnClickListener{
                removeData()
                showData()
            }

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

            btnDebugTips.isEnabled = true
            btnDebugTips.setOnClickListener {
                moveTo(TipsActivity::class.java)
            }

            btnDebugNotif.isEnabled = true
            btnDebugNotif.setOnClickListener {
                moveTo(NotificationActivity::class.java)
            }


        }
    }

    // Simplifikasi
    private fun moveTo(nextActivity: Class<*>) {
        startActivity(Intent(currentActivity, nextActivity))
    }
}