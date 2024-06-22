package com.capstone.prettyU.View.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference

class ViewModelFactory(private val setting: LocalPreference) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(setting) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(setting) as T
        }
        if (modelClass.isAssignableFrom(FaceScanViewModel::class.java)) {
            return FaceScanViewModel(setting) as T
        }
//        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
//            return RegisterViewModel(setting) as T
//        }
        throw IllegalArgumentException("ViewModel Factory Error ${modelClass.classes}")
    }
}