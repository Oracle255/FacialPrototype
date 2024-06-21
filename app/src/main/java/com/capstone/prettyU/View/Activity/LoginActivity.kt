package com.capstone.prettyU.View.Activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.prettyU.BackEnd.Utilities.Constant.AnimationConstant.AnimationProperty
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference
import com.capstone.prettyU.BackEnd.Utilities.Utils
import com.capstone.prettyU.View.ViewModel.LoginViewModel
import com.capstone.prettyU.View.ViewModel.ViewModelFactory
import com.capstone.prettyU.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var localPref: LocalPreference
    private var edContentUsername: String = ""
    private var edContentPassword: String = ""
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        localPref = LocalPreference(this)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(localPref))[LoginViewModel::class.java]
        setContentView(binding.root)
        playAnimation()
        binding.tvNotif.text =
            "name : ${localPref.fetchName()} \n\n token : ${localPref.fetchToken()}" // debug
        scope.launch {
            edtListener()
            btnLifeCycle()
        }
    }

//    fun updateUi() {
//
//    }

    private fun observeViewModel() {
        val errorStateObserver = {state: Boolean ->
            if (state) {
                //Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                Utils().intentDialogBuilder(
                    this@LoginActivity,
                    "SUCCESS",
                    "",
                    true,
                    MainActivity::class.java
                )
            } else {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT)
            }
            viewModel.errorState.removeObservers(this)
        }

        val loginResultObserver = { result: String ->

            // LOGIC CHECK MASIH SEMPRAWUT
            if (result == "success") {
                //loadBar(false)
                Utils().intentDialogBuilder(
                    this@LoginActivity,
                    "SUCCESS",
                    "",
                    true,
                    MainActivity::class.java
                )
            } else {
                Toast.makeText(this, "GAGAL MANING", Toast.LENGTH_SHORT).show()
                //loadBar(false)
                //Utils().simpleDialogBuilder(this@LoginActivity, "GAGAL", result)
            }
            viewModel.loginResult.removeObservers(this)
        }

        val errorMEssageObserver = { message: String? ->
            if (message != null) {
                Toast.makeText(this, "error $message", Toast.LENGTH_SHORT).show()
            }
            viewModel.clearErrorMessage()
            //viewModel.errorMessage.removeObserver(this)
        }

        //viewModel.loginResult.observe(this, loginResultObserver)
        viewModel.errorState.observe(this, errorStateObserver)
        viewModel.errorMessage.observe(this, errorMEssageObserver)
    }

    private fun btnLifeCycle() {
        binding.btnSignIn.setOnClickListener {
            observeViewModel()
            val username = edContentUsername
            val password = edContentPassword
            viewModel.login(username, password)
        }
    }

    private fun edtListener() {
        binding.edLoginUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                edContentUsername = "$s"
                //updateUi()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                edContentUsername = "$s"
                //updateUi()
            }

            override fun afterTextChanged(s: Editable?) {
                edContentUsername = "$s"
                //updateUi()
            }

        })
        binding.edLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                edContentPassword = "$s"
                //updateUi()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                edContentPassword = "$s"
                //updateUi()
            }

            override fun afterTextChanged(s: Editable?) {
                edContentPassword = "$s"
                //updateUi()
            }

        })
    }

    // TODO: set animasi
    private fun playAnimation() {
        val const = AnimationProperty
        // set posisi awal sebelum start
        binding.inputLoginUsername.translationX = 400f
        binding.edLoginUsername.translationX = 400f
        binding.inputLoginPassword.translationX = -400f
        binding.edLoginPassword.translationX = -400f
        binding.btnSignIn.translationY = 1200f

        val tvNotif =
            ObjectAnimator.ofFloat(binding.tvNotif, View.ALPHA, 0f, 1f)
                .setDuration(const.animDurationShort)

        val inputLoginUsername =
            ObjectAnimator.ofFloat(binding.inputLoginUsername, View.TRANSLATION_X, 400f, 0f)
                .setDuration(const.animDurationShort)

        val edLoginUsername =
            ObjectAnimator.ofFloat(binding.edLoginUsername, View.TRANSLATION_X, 400f, 0f)
                .setDuration(const.animDurationShort)

        val inputLoginPassword =
            ObjectAnimator.ofFloat(binding.inputLoginPassword, View.TRANSLATION_X, -400f, 0f)
                .setDuration(const.animDurationShort)

        val edLoginPassword =
            ObjectAnimator.ofFloat(binding.edLoginPassword, View.TRANSLATION_X, -400f, 0f)
                .setDuration(const.animDurationShort)

        val btnSignIn = ObjectAnimator.ofFloat(binding.btnSignIn, View.TRANSLATION_Y, 1200f, 0f)
            .setDuration(const.animDurationShort)

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