package com.capstone.prettyU.View.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.prettyU.BackEnd.Utilities.Constant.DevTestConfig.Companion.loremParagraph
import com.capstone.prettyU.BackEnd.Utilities.Constant.DevTestConfig.Companion.loremWithLength
import com.capstone.prettyU.BackEnd.Utilities.Utils
import com.capstone.prettyU.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTipsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentString()
        //setContent()
//        if (debugMode) {
//            loremTest()
//        }

    }

    private fun loremTest() {
        binding.apply {
            tvTitle.text = loremWithLength(1)
            tvContent.text = loremParagraph(9, 15)
            tvContent2.text = loremParagraph(100, 500)
        }
    }

    private fun setContent() {
        val req = intent.getStringExtra(REQUEST_CODE)

        fun content1() {
            binding.tvTitle.text = "DEFAULT 1"
            binding.tvContent.text = "TITLE 1-1 lr"
            binding.tvContent2.text = "TITLE 2-1 lr"
        }

        fun content2() {
            binding.tvTitle.text = "TITLE 2 lr"
            binding.tvContent.text = "TITLE 2-1 lr"
            binding.tvContent2.text = "TITLE 2-2 lr"
        }

        fun content3() {
            binding.tvTitle.text = "TITLE 3 lr"
            binding.tvContent.text = "TITLE 3-1 lr"
            binding.tvContent2.text = "TITLE 3-2 lr"
        }

        when (req) {
            "1" -> content1()
            "2" -> content2()
            "3" -> content3()

        }
    }

    fun setContentString() {
        val req = intent.getStringExtra(REQUEST_CODE)
        val contentValue = req?.let { Utils().setTipsContent(this, it) }
        val title = contentValue?.get(0)
        val content1 = contentValue?.get(1)
        val content2 = contentValue?.get(2)
        binding.tvTitle.text = title
        binding.tvContent.text = content1
        binding.tvContent2.text = content2

    }

    companion object {
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_MESSAGE = "EXTRA_MESSAGE"
        const val REQUEST_CODE = "3"
    }
}



