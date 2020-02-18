package com.example.finalproject.ui.login

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.model.LoginRequest
import com.example.finalproject.ultis.ValidationCheck

class LoginPresenter constructor(private val firebaseAuthInterface: FirebaseAuthInterface) :
    LogInContract.Presenter {

    private lateinit var mView: LogInContract.View
    private var mLoginRequest = LoginRequest()

    override fun onSubmitLogin() {
        mView.showProgressBar()
        if (ValidationCheck.isLoginValid(mLoginRequest)) {
            firebaseAuthInterface.login(
                mLoginRequest.email.toString(),
                mLoginRequest.password.toString()
            ) { isSuccess, e ->
                if (isSuccess) mView.onLoginSuccess() else mView.showLoginError(e)
            }
        }
    }

    override fun onEmailChange(email: String) {
        if (!ValidationCheck.isEmailValid(email)) {
            mView.showEmailError()
        }
        mLoginRequest.email = email
    }

    override fun onPasswordChange(password: String) {
        if (!ValidationCheck.isPasswordValid(password)) {
            mView.showPasswordError()
        }
        mLoginRequest.password = password
    }

    override fun setView(view: LogInContract.View) {
        this.mView = view
    }

    override fun onStart() = Unit

    override fun onStop() = Unit
}
