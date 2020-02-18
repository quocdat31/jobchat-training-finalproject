package com.example.finalproject.ui.register

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.firebase.database.FirebaseDatabaseInterface
import com.example.finalproject.model.RegisterRequest
import com.example.finalproject.ultis.ValidationCheck


class RegisterPresenter constructor(
    private val firebaseAuthInterface: FirebaseAuthInterface,
    private val firebaseDatabaseInterface: FirebaseDatabaseInterface
    ) : RegisterContract.Presenter {

    private lateinit var mView: RegisterContract.View
    private val mUser = RegisterRequest()

    val REQUEST_CODE = 1
    val TITLE = "Select image"

    override fun onUsernameChange(username: String) {
        mUser.name = username
    }

    override fun onEmailChange(email: String) {
        if (!ValidationCheck.isEmailValid(email)) {
            mView.showEmailError()
        }
        mUser.email = email
    }

    override fun onPasswordChange(password: String) {
        if (!ValidationCheck.isPasswordValid(password)) {
            mView.showPasswordError()
        }

        mUser.password = password
    }

    override fun onConfirmPasswordChange(confirmPassword: String) {
        if (!ValidationCheck.isConfirmPasswordMatch(mUser.password.toString(), confirmPassword)) {
            mView.showPasswordMatchingError()
        }
        mUser.confirmPassword = confirmPassword
    }

    override fun onSubmitRegister() {
        if (ValidationCheck.isRegisterValid(mUser)) {
            mView.showProgressBar()
            val (name, email, password) = mUser
            firebaseAuthInterface.register(
                email.toString(),
                password.toString(),
                name.toString()
            ) { onResult, exception ->
                if (onResult) {
                    val id = firebaseAuthInterface.getUserId()
                    firebaseDatabaseInterface.createUser(
                        id,
                        name.toString(),
                        email.toString()
                    ) { onResult, exception ->
                        if (onResult) mView.onRegisterSuccess() else mView.onRegisterError(
                            exception
                        )
                    }
                } else mView.onRegisterError(exception)
            }
        }
    }

    override fun accessGallery(context: Context) {

        val intent = Intent().apply {
            type="image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(context as Activity, Intent.createChooser(intent, TITLE),REQUEST_CODE, null)
    }

    override fun setView(view: RegisterContract.View) {
        this.mView = view
    }

    override fun onStart() = Unit

    override fun onStop() = Unit



}
