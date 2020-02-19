package com.example.finalproject.model

import com.example.finalproject.ultis.ValidationCheck

data class LoginModel(var email: String? = null, var password: String? = null) {
    fun isValid() =
        ValidationCheck.isEmailValid(email.toString()) && ValidationCheck.isPasswordValid(password.toString())
}
