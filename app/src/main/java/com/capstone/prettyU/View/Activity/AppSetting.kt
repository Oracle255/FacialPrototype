package com.capstone.prettyU.View.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.prettyU.R
import com.capstone.prettyU.databinding.ActivityAppSettingBinding

class AppSetting : AppCompatActivity() {
    private lateinit var binding: ActivityAppSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}