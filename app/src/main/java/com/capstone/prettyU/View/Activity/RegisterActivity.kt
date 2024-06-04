package com.capstone.prettyU.View.Activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.capstone.prettyU.BackEnd.Utilities.Constant.AnimationConstant
import com.capstone.prettyU.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playAnimation()

    }

    // TODO: set animasi (mostly) done
    private fun playAnimation() {
        val const = AnimationConstant
        // INIT LOKASI
        binding.ivRegisterLogo.translationX = -400f
        binding.tvNotifRegister.translationX = 400f


        binding.inputUsername.translationX = -400f
        binding.edRegisterUsername.translationX = -400f
        binding.inputEmail.translationX = 400f
        binding.edRegisterEmail.translationX = 400f
        binding.inputPassword.translationX = -400f
        binding.edRegisterPassword.translationX = -400f
        binding.inputPasswordCheck.translationX = -400f
        binding.edRegisterPassCheck.translationX = -400f

        binding.btnSignIn.translationY = 400f


        val ivRegisterLogo =
            ObjectAnimator.ofFloat(binding.ivRegisterLogo, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationMedium)
        val tvNotifRegister =
            ObjectAnimator.ofFloat(binding.tvNotifRegister, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationShort)

        val inputUsername =
            ObjectAnimator.ofFloat(binding.inputUsername, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationShort)
        val edRegisterUsername =
            ObjectAnimator.ofFloat(binding.edRegisterUsername, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationMedium)

        val inputEmail =
            ObjectAnimator.ofFloat(binding.inputEmail, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationShort)
        val edRegisterEmail =
            ObjectAnimator.ofFloat(binding.edRegisterEmail, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationMedium)

        val inputPassword =
            ObjectAnimator.ofFloat(binding.inputPassword, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationShort)
        val edRegisterPassword =
            ObjectAnimator.ofFloat(binding.edRegisterPassword, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationMedium)

        val inputPasswordCheck =
            ObjectAnimator.ofFloat(binding.inputPasswordCheck, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationShort)
        val edRegisterPassCheck =
            ObjectAnimator.ofFloat(binding.edRegisterPassCheck, View.TRANSLATION_X, 0f)
                .setDuration(const.animDurationMedium)

        val btnSignIn =
            ObjectAnimator.ofFloat(binding.btnSignIn, View.TRANSLATION_Y, 0f)
                .setDuration(const.animDurationMedium)

        val pairUsername = AnimatorSet().apply {
            playTogether(inputUsername, edRegisterUsername)
        }
        val pairEmail = AnimatorSet().apply {
            playTogether(inputEmail, edRegisterEmail)
        }
        val pairPassword = AnimatorSet().apply {
            playTogether(inputPassword, edRegisterPassword)
        }
        val pairPasswordCheck = AnimatorSet().apply {
            playTogether(inputPasswordCheck, edRegisterPassCheck)
        }

        AnimatorSet().apply {
            playSequentially(
                tvNotifRegister,
                ivRegisterLogo,
                pairUsername,
                pairEmail,
                pairPassword,
                pairPasswordCheck,
                btnSignIn
            )
            start()
        }
    }
}