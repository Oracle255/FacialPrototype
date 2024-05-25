package com.capstone.prettyU.ProtoActivity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.capstone.prettyU.R
import com.capstone.prettyU.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playAnimation()
    }

    // TODO: set animasi
    private fun playAnimation() {
        // set posisi awal sebelum start
        binding.inputLoginUsername.translationX = 400f
        binding.edLoginUsername.translationX = 400f
        binding.inputLoginPassword.translationX = -400f
        binding.edLoginPassword.translationX = -400f
        binding.btnSignIn.translationY = 1200f

        val tvNotif =
            ObjectAnimator.ofFloat(binding.tvNotif, View.ALPHA, 0f, 1f).setDuration(300)

        val inputLoginUsername =
            ObjectAnimator.ofFloat(binding.inputLoginUsername, View.TRANSLATION_X, 400f, 0f)
                .setDuration(200)

        val edLoginUsername =
            ObjectAnimator.ofFloat(binding.edLoginUsername, View.TRANSLATION_X, 400f, 0f)
                .setDuration(300)

        val inputLoginPassword =
            ObjectAnimator.ofFloat(binding.inputLoginPassword, View.TRANSLATION_X, -400f, 0f)
                .setDuration(400)

        val edLoginPassword =
            ObjectAnimator.ofFloat(binding.edLoginPassword, View.TRANSLATION_X, -400f, 0f)
                .setDuration(500)

        val btnSignIn = ObjectAnimator.ofFloat(binding.btnSignIn, View.TRANSLATION_Y, 1200f, 0f)
            .setDuration(600)

        val pairUsername = AnimatorSet().apply {
            playTogether(inputLoginUsername, edLoginUsername)
        }
        val pairPassword = AnimatorSet().apply {
            playTogether(inputLoginPassword, edLoginPassword)
        }
        val pairAll = AnimatorSet().apply {
            playTogether(pairUsername, pairPassword, btnSignIn)
        }

        AnimatorSet().apply {
            playSequentially(tvNotif, pairAll)
            start()
        }
    }
}