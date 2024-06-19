package com.capstone.prettyU.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.prettyU.R
import com.capstone.prettyU.databinding.ActivityDebugLandingBinding
import com.capstone.prettyU.databinding.ActivityTreatmentItemBinding

class TreatmentItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTreatmentItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreatmentItemBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    companion object {
        const val CONTENT_TITLE = "CONTENT_TITLE"
    }
}