package com.capstone.prettyU.View.Activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.prettyU.BackEnd.Utilities.Constant.AnimationConstant
import com.capstone.prettyU.BackEnd.Utilities.Constant.DevTestConfig
import com.capstone.prettyU.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private var animationIsFinished = false
    private val testingMode = DevTestConfig.testingMode
    private val animationExtraDelay: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        binding.apply {

        }

    }

    private fun playAnimation() {
        val const = AnimationConstant
        // INITIALIZE STARTING PARAMETER
        binding.ivAppName.scaleX = 0f
        binding.ivAppName.scaleY = 0f
        binding.tvMessage1.translationX = 400f
        binding.tvMessage2.translationX = -400f

        val ivAppNameScaleX =
            ObjectAnimator.ofFloat(binding.ivAppName, View.SCALE_X, 1f)
                .setDuration(const.animDurationExtraLong)
        val ivAppNameScaleY =
            ObjectAnimator.ofFloat(binding.ivAppName, View.SCALE_Y, 1f)
                .setDuration(const.animDurationExtraLong)
        val tvMessage1TranslationY =
            ObjectAnimator.ofFloat(binding.tvMessage1, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationLong)
        val tvMessage2TranslationY =
            ObjectAnimator.ofFloat(binding.tvMessage2, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationLong)

        val emptyAnimation =
            ObjectAnimator.ofFloat(binding.ivAppName, View.ALPHA, 1f)
                .setDuration(animationExtraDelay)

        val pairAnimIvAppName = AnimatorSet().apply {
            playTogether(ivAppNameScaleX, ivAppNameScaleY)
        }
        val pairAll = AnimatorSet().apply {
            playTogether(pairAnimIvAppName, tvMessage1TranslationY,tvMessage2TranslationY)
        }

        AnimatorSet().apply {
            playSequentially(pairAll, emptyAnimation)
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    animationIsFinished = true
                    checkIntent()
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
            start()
        }
    }

    private fun checkIntent() {
        // Set Logic intent class disini
        if (testingMode) {
            if (animationIsFinished) {
                startActivity(Intent(this, _DebugLandingActivity::class.java))
                finish()
            }
        } else {
            if (animationIsFinished) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}