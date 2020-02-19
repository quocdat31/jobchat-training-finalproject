package com.example.finalproject.firebase.database

import android.util.Log
import com.example.finalproject.model.Friend
import com.example.finalproject.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class FirebaseDatabaseImp @Inject constructor(private var firebaseDatabase: FirebaseDatabase) :
    FirebaseDatabaseInterface {

    override fun createUser(id: String, name: String, email: String) {
        val user = User(id, name, email)
        firebaseDatabase.reference.child(CHILD_USER_PATH).child(id).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful) {
                    //TODO:
                }

            }
            .addOnFailureListener { exception ->
                //TODO: Toast e
            }
    }

    override fun getProfile(id: String, onResult: User) {
        firebaseDatabase.reference.child(CHILD_USER_PATH).child(id)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val user = p0.getValue(User::class.java)
                    Log.d("asd", user.toString())
                }
            })
    }

    override fun addFriend(id: String, friend: Friend, onResult: (Boolean) -> Unit) {
        val friendReference =
            firebaseDatabase.reference.child(id).child(CHILD_USER_FRIEND_PATH).push()
        val friendKey = friendReference.key
        if (friendKey != null) {
            val friendCopy = friend.copy(id = friendKey)
            friendReference.setValue(friendCopy).addOnCompleteListener { task ->
                onResult(task.isSuccessful == task.isComplete)
            }
        }
    }

    override fun getFriendList(id: String, onResult: (List<Friend>) -> Unit) {
        firebaseDatabase.reference.child(CHILD_USER_PATH).child(id).child(CHILD_USER_FRIEND_PATH)
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {



                }

                override fun onDataChange(p0: DataSnapshot) {



                }
            })
    }

    companion object {
        const val CHILD_USER_PATH = "users"
        const val CHILD_USER_FRIEND_PATH = "friends"
    }
}
