package com.capstone.prettyU.ProtoActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.prettyU.databinding.ActivityProtoLandingBinding

class ProtoLanding : AppCompatActivity() {
    private lateinit var binding: ActivityProtoLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProtoLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // TODO: set animasi
    private fun playAnimation() {

    }
}