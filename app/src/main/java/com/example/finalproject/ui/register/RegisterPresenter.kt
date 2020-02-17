package com.example.finalproject.ui.register

import android.util.Log
import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.model.User
import com.example.finalproject.ultis.ValidationCheck

class RegisterPresenter constructor(
    private val firebaseAuthInterface: FirebaseAuthInterface
) : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View
    private val user = User()

    override fun onUsernameChange(username: String) {
        user.name = username
    }

    override fun onEmailChange(email: String) {
        if (!ValidationCheck.isEmailValid(email)) {
            view.showEmailError()
        }
        user.email = email
    }

    override fun onPasswordChange(password: String) {
        if (!ValidationCheck.isPasswordValid(password)) {
            view.showPasswordError()
        }

        user.password = password
    }

    override fun onConfirmPasswordChange(confirmPassword: String) {
        if (!ValidationCheck.isConfirmPasswordMatch(user.password.toString(), confirmPassword)) {
            view.showPasswordMatchingError()
        }
        user.confirmPassword = confirmPassword
    }

    override fun onSubmitRegister() {
        if (user.isValid()) {
            val (name, email, password) = user
            firebaseAuthInterface.register(
                email.toString(),
                password.toString(),
                name.toString()
            ) { onResult, exception ->
                if (onResult)
                    view.onRegisterSuccess()
                else view.onRegisterError(exception)
            }
        }
    }

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun onStart() = Unit

    override fun onStop() = Unit

}
