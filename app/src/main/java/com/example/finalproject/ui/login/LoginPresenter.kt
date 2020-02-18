package com.example.finalproject.ui.login

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.model.LoginRequest
import com.example.finalproject.ultis.ValidationCheck
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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

//        firebaseAuthInterface.loginRx(
//            mLoginRequest.email.toString(),
//            mLoginRequest.password.toString()
//        ) { isSuccess, e ->
//        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

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
