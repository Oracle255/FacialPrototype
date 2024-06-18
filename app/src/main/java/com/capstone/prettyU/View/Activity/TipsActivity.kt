package com.capstone.prettyU.View.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.prettyU.BackEnd.Utilities.Constant.DevTestConfig.Companion.debugMode
import com.capstone.prettyU.BackEnd.Utilities.Constant.DevTestConfig.Companion.loremParagraph
import com.capstone.prettyU.BackEnd.Utilities.Constant.DevTestConfig.Companion.loremWithLength
import com.capstone.prettyU.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTipsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (debugMode) {
            loremTest()
        }

    }

    private fun loremTest() {
        binding.apply {
            tvTitle.text = loremWithLength(1)
            tvContent.text = loremParagraph(9, 15)
            tvContent2.text = loremParagraph(100, 500)
        }
    }
}