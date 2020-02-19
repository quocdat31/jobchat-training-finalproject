package com.example.finalproject.ui.register

import com.example.finalproject.base.BasePresenter
import com.example.finalproject.base.BaseView

interface RegisterContract {
    interface View : BaseView {
        fun onRegisterSuccess()
        fun onRegisterError(e: Exception?)
        fun showPasswordError()
        fun showEmailError()
        fun showPasswordMatchingError()
        fun showProgressBar()
    }

    interface Presenter : BasePresenter<View> {
        fun onUsernameChange(username: String)
        fun onEmailChange(email: String)
        fun onPasswordChange(password: String)
        fun onConfirmPasswordChange(confirmPassword: String)
        fun onSubmitRegister()
    }

    interface Navigator {
        fun navigateMainScreen()
    }
}
