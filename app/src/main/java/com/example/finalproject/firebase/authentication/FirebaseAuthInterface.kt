package com.example.finalproject.firebase.authentication

import kotlin.Exception

interface FirebaseAuthInterface {

    fun register(email: String, password: String, username: String, isSuccessful: (Boolean, Exception?) -> Unit)

    fun login(email: String, password: String, isSuccessful: (Boolean, Exception?) -> Unit)

    fun getUserId(): String

    fun getUsername(): String

    fun getUserEmail(): String

    fun logout(isSuccessful: () -> Unit)
}
