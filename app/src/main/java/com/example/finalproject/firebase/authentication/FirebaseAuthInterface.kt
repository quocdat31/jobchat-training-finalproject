package com.example.finalproject.firebase.authentication

import kotlin.Exception

interface FirebaseAuthInterface {

    fun register(email: String, password: String, username: String, onResult: (Boolean, Exception?) -> Unit)

    fun login(email: String, password: String, onResult: (Boolean, Exception?) -> Unit)

    fun getUserId(): String

    fun getUsername(): String

    fun logout(onResult: () -> Unit)
}
