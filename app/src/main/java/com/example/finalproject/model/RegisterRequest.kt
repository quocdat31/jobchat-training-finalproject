package com.example.finalproject.model

data class RegisterRequest(
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var confirmPassword: String? = null
)
