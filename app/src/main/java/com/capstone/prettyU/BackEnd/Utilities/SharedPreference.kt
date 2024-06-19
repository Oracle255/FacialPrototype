package com.capstone.prettyU.BackEnd.Utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    companion object {
        const val PREF_KEY = "SETTING"
        const val TOKEN = "PREF_TOKEN"
        const val NAME = "PREF_NAME"
        const val EMAIL = "PREF_EMAIL"
        const val BIO = "PREF_BIO"
    }
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun addToken(input: String) {
        return editor.putString(TOKEN, input).apply()
    }
    fun fetchToken(): String? {
        return sharedPreferences.getString(TOKEN, TOKEN)
    }
    fun removeToken() {
        return editor.putString(TOKEN, "").apply()
    }

    fun addName(input: String) {
        return editor.putString(NAME, input).apply()
    }
    fun fetchName(): String? {
        return sharedPreferences.getString(NAME, NAME)
    }
    fun removeName() {
        return editor.putString(NAME, "").apply()
    }

    fun addEmail(input: String) {
        return editor.putString(EMAIL, input).apply()
    }
    fun fetchEmail(): String? {
        return sharedPreferences.getString(EMAIL, EMAIL)
    }
    fun removeEmail() {
        return editor.putString(EMAIL, "").apply()
    }

    fun addBio(input: String) {
        return editor.putString(BIO, input).apply()
    }
    fun fetchBio(): String? {
        return sharedPreferences.getString(BIO, BIO)
    }
    fun removeBio() {
        return editor.putString(BIO, "").apply()
    }
}