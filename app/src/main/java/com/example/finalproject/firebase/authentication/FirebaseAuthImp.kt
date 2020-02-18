package com.example.finalproject.firebase.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject
import kotlin.Exception

class FirebaseAuthImp @Inject constructor(
    val firebaseAuth: FirebaseAuth
) : FirebaseAuthInterface {

    override fun register(
        email: String,
        password: String,
        username: String,
        onResult: (Boolean, Exception?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isComplete && task.isSuccessful) {
                    firebaseAuth.currentUser?.updateProfile(
                        UserProfileChangeRequest
                            .Builder()
                            .setDisplayName(username)
                            .build()
                    )
//                    val token = task.result?.user?.getIdToken(true)?.result?.token
//                    Log.d("token", token.toString())
                    onResult(true, null)

                }
            }
            .addOnFailureListener { exception ->
                onResult(false, exception)
            }
    }

    override fun login(
        email: String,
        password: String,
        onResult: (Boolean, Exception?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                onResult(task.isSuccessful && task.isComplete, null)
            }
            .addOnFailureListener { e ->
                onResult(false, e)
            }
    }

    override fun getUserId(): String = firebaseAuth.currentUser?.uid.toString()

    override fun getUsername(): String = firebaseAuth.currentUser?.displayName.toString()

    override fun logout(onResult: () -> Unit) {
        firebaseAuth.signOut()
        onResult()
    }
}
