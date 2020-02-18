package com.example.finalproject.ui.login

import com.example.finalproject.base.BasePresenter
import com.example.finalproject.base.BaseView
import kotlin.Exception

interface LogInContract {
    interface View : BaseView {
        fun showProgressBar()
        fun showPasswordError()
        fun showEmailError()
        fun showLoginError(exception: Exception?)
        fun onLoginSuccess()
        fun onSignUpButtonClick()
    }

    interface Presenter : BasePresenter<View> {
        fun onSubmitLogin()
        fun onEmailChange(email: String)
        fun onPasswordChange(password: String)
    }

    interface Navigator {
        fun navigateHomeScreen()
        fun navigateSignUpScreen()
    }
}
