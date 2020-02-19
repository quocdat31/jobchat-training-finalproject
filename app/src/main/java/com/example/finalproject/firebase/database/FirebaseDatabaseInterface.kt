package com.example.finalproject.firebase.database

import com.example.finalproject.model.Friend
import com.example.finalproject.model.User

interface FirebaseDatabaseInterface {
    fun createUser(id: String, name: String, email: String)
    fun getProfile(id: String, onResult: (User))
    fun addFriend(id: String, friend: Friend, onResult: (Boolean) -> Unit)
    fun getFriendList(id: String, onResult: (List<Friend>) -> Unit)
}
