package com.example.finalproject.ui.register.login

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.model.LoginModel
import com.example.finalproject.ultis.ValidationCheck

class LoginPresenter constructor(private val firebaseAuthInterface: FirebaseAuthInterface) :
    LogInContract.Presenter {

    private lateinit var view: LogInContract.View
    private var loginModel = LoginModel()

    override fun onSubmitLogin() {
        view.showProgressBar()
        if (loginModel.isValid()) {
            firebaseAuthInterface.login(
                loginModel.email.toString(),
                loginModel.password.toString()
            ) { isSuccess, e ->
                if (isSuccess) view.onLoginSuccess() else view.showLoginError(e)
            }
        }
    }

    override fun onEmailChange(email: String) {
        if (!ValidationCheck.isEmailValid(email)) {
            view.showEmailError()
        }
        loginModel.email = email
    }

    override fun onPasswordChange(password: String) {
        if (!ValidationCheck.isPasswordValid(password)) {
            view.showPasswordError()
        }
        loginModel.password = password
    }

    override fun setView(view: LogInContract.View) {
        this.view = view
    }

    override fun onStart() = Unit

    override fun onStop() = Unit
}
