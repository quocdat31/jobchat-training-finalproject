package com.example.finalproject.model

data class User(
    var id: String? = null,
    var name: String? = null,
    var email: String? = null,
    var friendList: List<Friend>? = null
)


