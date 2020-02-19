package com.example.finalproject.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R
import com.example.finalproject.registerPresenter
import com.example.finalproject.ultis.onTextChanged
import com.example.finalproject.ultis.toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    private val mPresenter by lazy { registerPresenter() }
    private lateinit var mNavigator: RegisterNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
        addEditTextListener()

    }

    override fun onRegisterSuccess() {
        this.toast(getString(R.string.onSuccess))
        mNavigator.navigateMainScreen()
        setVisibilityProgressBar(View.GONE)
    }

    override fun onRegisterError(e: Exception?) {
        setVisibilityProgressBar(View.GONE)
        submitRegisterButton.visibility = View.VISIBLE
        this.toast(e?.message.toString())
    }

    override fun showPasswordError() {
        registerPasswordEditText.setError(getString(R.string.passwordInputError), null)
    }

    override fun showEmailError() {
        registerEmailEditText.error = getString(R.string.emailInputError)
    }

    override fun showPasswordMatchingError() {
        registerConfirmPasswordEditText.setError(getString(R.string.confirmPasswordInputError), null)
    }

    override fun showProgressBar() {
        setVisibilityProgressBar(View.VISIBLE)
        submitRegisterButton.visibility = View.GONE
    }

    private fun initView() {
        mPresenter.setView(this)
        mNavigator = RegisterNavigator(this)
    }

    private fun addEditTextListener() {
        submitRegisterButton.setOnClickListener {
            mPresenter.onSubmitRegister()
        }

        registerEmailEditText.onTextChanged { email ->
            mPresenter.onEmailChange(email)
        }

        registerPasswordEditText.onTextChanged { password ->
            mPresenter.onPasswordChange(password)
        }

        registerConfirmPasswordEditText.onTextChanged { confirmPassword ->
            mPresenter.onConfirmPasswordChange(confirmPassword)
        }

        registerUsernameEditText.onTextChanged { username ->
            mPresenter.onUsernameChange(username)
        }
    }

    private fun setVisibilityProgressBar(visibility: Int){
        registerProgressBar.visibility = visibility
    }
}
