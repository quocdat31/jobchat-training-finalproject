package com.example.finalproject.ui.register

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.firebase.database.FirebaseDatabaseInterface
import com.example.finalproject.model.RegisterModel
import com.example.finalproject.ultis.ValidationCheck

class RegisterPresenter constructor(
    private val firebaseAuthInterface: FirebaseAuthInterface,
    private val firebaseDatabaseInterface: FirebaseDatabaseInterface
    ) : RegisterContract.Presenter {

    private lateinit var mView: RegisterContract.View
    private val mUser = RegisterModel()

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
        if (mUser.isValid()) {
            mView.showProgressBar()
            val (name, email, password) = mUser
            firebaseAuthInterface.register(
                email.toString(),
                password.toString(),
                name.toString()
            ) { onResult, exception ->
                if (onResult) {
                    val id = firebaseAuthInterface.getUserId()
                    firebaseDatabaseInterface.createUser(id, name.toString(), email.toString())
                    mView.onRegisterSuccess()
                } else mView.onRegisterError(exception)
            }
        }
    }

    override fun setView(view: RegisterContract.View) {
        this.mView = view
    }

    override fun onStart() = Unit

    override fun onStop() = Unit

}
