package com.example.finalproject.firebase.database

import com.example.finalproject.model.Friend
import com.example.finalproject.model.FriendResponse
import com.example.finalproject.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject


class FirebaseDatabaseImp @Inject constructor(private var firebaseDatabase: FirebaseDatabase) :
    FirebaseDatabaseInterface {

    override fun createUser(
        id: String,
        name: String,
        email: String,
        isSuccessful: (Boolean, Exception?) -> Unit
    ) {
        val user = User(id, name, email)
        firebaseDatabase.reference.child(CHILD_USER_PATH).child(id).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful) {
                    isSuccessful(true, null)
                }
            }
            .addOnFailureListener { exception ->
                isSuccessful(false, exception)
            }
    }

    override fun getProfile(id: String, isSuccessful: User) {
        firebaseDatabase.reference.child(CHILD_USER_PATH).child(id)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
//                    val user = p0.getValue(User::class.java)
                }
            })
    }


    override fun getFriendList(id: String, isSuccessful: (List<Friend>, DatabaseError?) -> Unit) {
        firebaseDatabase.reference.child(CHILD_USER_PATH).child(id).child(CHILD_USER_FRIEND_PATH)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) = isSuccessful(listOf(), p0)
                override fun onDataChange(p0: DataSnapshot) {
                    val friendList = ArrayList<Friend>()
                    for (item in p0.children) {
                        val friend = item.getValue(Friend::class.java)
                        if (friend != null) {
                            friendList.add(friend)
                        }
                    }
                    isSuccessful(friendList, null)
                }
            })
    }

    override fun addFriend(
        id: String,
        friendEmail: String,
        isSuccessful: (Boolean, String?) -> Unit
    ) {
        val friendReference = firebaseDatabase.reference.child(CHILD_USER_PATH).child(id)
            .child(CHILD_USER_FRIEND_PATH).push()
        val requestId = friendReference.key
        if (requestId != null) {
            val query =
                firebaseDatabase.reference.child(CHILD_USER_PATH).orderByChild(CHILD_EMAIL)
                    .equalTo(friendEmail)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) = Unit
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        for (dataSnapshot in p0.children) {
                            val data = dataSnapshot.getValue(FriendResponse::class.java)
                            if (data != null) {
                                val friend = Friend(
                                    id = data.id,
                                    name = data.name,
                                    email = data.email,
                                    status = ADD_FRIEND_REQUEST_STATUS
                                )
                                friendReference.setValue(friend).addOnCompleteListener { task ->
                                    if (task.isComplete && task.isSuccessful)
                                        isSuccessful(true, null)
                                }
                                    .addOnFailureListener { exception ->
                                        isSuccessful(false, exception.message)
                                    }
                            }
                        }
                    } else isSuccessful(false, "$friendEmail Does not exists")
                }
            })
        }
    }

    companion object {
        const val CHILD_USER_PATH = "users"
        const val CHILD_USER_FRIEND_PATH = "friends"
        const val CHILD_EMAIL = "email"

        const val ADD_FRIEND_REQUEST_STATUS = "Waiting for response"

    }
}
