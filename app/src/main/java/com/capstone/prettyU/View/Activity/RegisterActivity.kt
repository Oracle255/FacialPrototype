package com.capstone.prettyU.View.Activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.prettyU.BackEnd.Utilities.Constant.AnimationConstant
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference
import com.capstone.prettyU.View.ViewModel.RegisterViewModel
import com.capstone.prettyU.View.ViewModel.ViewModelFactory
import com.capstone.prettyU.databinding.ActivityRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var localPref: LocalPreference
    private val scope = CoroutineScope(Dispatchers.IO)

    private var edtContentUserName = ""
    private var edtContentEmail = ""
    private var edtContentPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        localPref = LocalPreference(this)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(localPref))[RegisterViewModel::class.java]

        setContentView(binding.root)
        playAnimation()

        scope.launch {
            edtListener()
            binding.btnSignIn.setOnClickListener{
                observeViewModel()
                val username = edtContentUserName
                val email = edtContentEmail
                val password = edtContentPassword
                viewModel.register(email, username, password)
            }
        }

    }

    private fun observeViewModel() {
        val errorObserver = {state: Boolean ->
            if (state) {
                // TODO(Pindah ke activity Login jika sukses)
                Toast.makeText(this,"Success", Toast.LENGTH_SHORT)
            }
        }

        val resultObserver = {result: String ->
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
//            if (result) {
//
//            }
            //viewModel.registerResult.removeObserver(this)
        }
        val errorMessageObserver = {message: String? ->
            Toast.makeText(this, "GAGAL", Toast.LENGTH_SHORT)
            viewModel.clearErrorMessage()
            //viewModel.errorMessage.removeObserver(this)
        }
        viewModel.registerResult.observe(this, resultObserver)
        viewModel.errorMessage.observe(this, errorMessageObserver)

    }

    private fun updateUi() {

    }

    private fun edtListener() {
        binding.edRegisterEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                edtContentEmail = "$s"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                edtContentEmail = "$s"
            }

            override fun afterTextChanged(s: Editable?) {
                edtContentEmail = "$s"
            }

        })

        binding.edRegisterUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                edtContentUserName = "$s"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                edtContentUserName = "$s"
            }

            override fun afterTextChanged(s: Editable?) {
                edtContentUserName = "$s"
            }

        })

        binding.edRegisterPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                edtContentPassword = "$s"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                edtContentPassword = "$s"
            }

            override fun afterTextChanged(s: Editable?) {
                edtContentPassword = "$s"
            }

        })
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
        //binding.edRegisterPassCheck.hint = ""

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
        //val edRegisterUsernameHint =

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