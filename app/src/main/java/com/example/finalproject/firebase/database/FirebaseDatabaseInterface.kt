package com.example.finalproject.firebase.database

import com.example.finalproject.model.Friend
import com.example.finalproject.model.User
import com.google.firebase.database.DatabaseError
import java.lang.Exception

interface FirebaseDatabaseInterface {
    fun createUser(id: String, name: String, email: String, isSuccessful: (Boolean, Exception?) -> Unit)
    fun getProfile(id: String, isSuccessful: (User))
    fun addFriend(id: String, friendEmail: String, isSuccessful: (Boolean, String?) -> Unit)
    fun getFriendList(id: String, isSuccessful: (List<Friend>, DatabaseError?) -> Unit)
}
