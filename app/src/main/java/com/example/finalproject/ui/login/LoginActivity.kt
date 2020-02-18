package com.example.finalproject.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R
import com.example.finalproject.loginPresenter
import com.example.finalproject.ultis.onTextChanged
import com.example.finalproject.ultis.toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LogInContract.View {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private val mPresenter by lazy { loginPresenter() }
    private lateinit var mNavigator: LoginNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        addEditTextListener()

    }

    override fun showProgressBar() {
        submitLoginButton.visibility = View.GONE
        loginProgressBar.visibility = View.VISIBLE
    }

    override fun showPasswordError() {
        signInPasswordEditText.setError(getString(R.string.passwordInputError), null)
    }

    override fun showEmailError() {
        signInEmailEditText.error = getString(R.string.emailInputError)
    }

    override fun showLoginError(exception: Exception?) {
        this.toast(exception?.message.toString())
        if (submitLoginButton.visibility == View.GONE) {
            submitLoginButton.visibility = View.VISIBLE
            loginProgressBar.visibility = View.GONE
        }
    }

    override fun onLoginSuccess() {
        loginProgressBar.visibility = View.GONE
        mNavigator.navigateHomeScreen()
    }

    override fun onSignUpButtonClick() {
        mNavigator.navigateSignUpScreen()
    }

    private fun initView() {
        mPresenter.setView(this)
        mNavigator = LoginNavigator(this)
    }

    private fun addEditTextListener() {
        signInEmailEditText.onTextChanged { email ->
            mPresenter.onEmailChange(email)
        }
        signInPasswordEditText.onTextChanged { password ->
            mPresenter.onPasswordChange(password)
        }
        submitLoginButton.setOnClickListener {
            mPresenter.onSubmitLogin()
        }
        signUpButton.setOnClickListener {
            onSignUpButtonClick()
        }
    }
}
