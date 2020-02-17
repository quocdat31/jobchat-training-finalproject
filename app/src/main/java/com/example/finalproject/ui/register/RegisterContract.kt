package com.example.finalproject.ui.register

import com.example.finalproject.BasePresenter
import com.example.finalproject.BaseView

interface RegisterContract {
    interface View : BaseView {
        fun onRegisterSuccess()
        fun onRegisterError(e: Exception?)
        fun showPasswordError()
        fun showEmailError()
        fun showPasswordMatchingError()
    }

    interface Presenter : BasePresenter<View> {
        fun onUsernameChange(username: String)
        fun onEmailChange(email: String)
        fun onPasswordChange(password: String)
        fun onConfirmPasswordChange(confirmPassword: String)
        fun onSubmitRegister()
    }
}
