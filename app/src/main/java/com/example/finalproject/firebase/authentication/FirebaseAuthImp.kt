package com.example.finalproject.firebase.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import io.reactivex.Observable
import javax.inject.Inject

class FirebaseAuthImp @Inject constructor(
    val firebaseAuth: FirebaseAuth
) : FirebaseAuthInterface {

    override fun register(
        email: String,
        password: String,
        username: String,
        isSuccessful: (Boolean, Exception?) -> Unit
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
                    isSuccessful(true, null)

                }
            }
            .addOnFailureListener { exception ->
                isSuccessful(false, exception)
            }
    }

    override fun login(
        email: String,
        password: String,
        isSuccessful: (Boolean, Exception?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                isSuccessful(task.isSuccessful && task.isComplete, null)
            }
            .addOnFailureListener { e ->
                isSuccessful(false, e)
            }
    }

    override fun getUserId(): String = firebaseAuth.currentUser?.uid.toString()

    override fun getUsername(): String = firebaseAuth.currentUser?.displayName.toString()

    override fun getUserEmail(): String = firebaseAuth.currentUser?.email.toString()


    override fun logout(isSuccessful: () -> Unit) {
        firebaseAuth.signOut()
        isSuccessful()
    }

    override fun loginRx(
        email: String,
        password: String,
        isSuccessful: (Boolean, Exception?) -> Unit
    ): Observable<Void> {

        return Observable.create { emitter ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isComplete && task.isSuccessful) {
                        emitter.onComplete()
                    }
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }


    }


}
