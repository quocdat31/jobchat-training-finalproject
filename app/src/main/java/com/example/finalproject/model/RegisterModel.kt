package com.example.finalproject.model

import com.example.finalproject.ultis.ValidationCheck

data class RegisterModel(
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var confirmPassword: String? = null
) {
    fun isValid(): Boolean =
        ValidationCheck.isEmailValid(email.toString()) && ValidationCheck.isConfirmPasswordMatch(
            password.toString(),
            confirmPassword.toString()
        )
}
