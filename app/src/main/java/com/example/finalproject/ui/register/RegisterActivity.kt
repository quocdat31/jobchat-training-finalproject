package com.example.finalproject.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R
import com.example.finalproject.registerPresenter
import com.example.finalproject.ultis.onTextChanged
import com.example.finalproject.ultis.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    private val presenter by lazy { registerPresenter() }
    private lateinit var navigator: RegisterNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
        addEditTextListener()

    }

    override fun onRegisterSuccess() {
        Log.d("", FirebaseAuth.getInstance().currentUser?.uid.toString())
        this.toast("Success")
        navigator.navigateMainScreen()
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
        presenter.setView(this)
        navigator = RegisterNavigator(this)
    }

    private fun addEditTextListener() {
        submitRegisterButton.setOnClickListener {
            presenter.onSubmitRegister()
        }

        registerEmailEditText.onTextChanged { email ->
            presenter.onEmailChange(email)
        }

        registerPasswordEditText.onTextChanged { password ->
            presenter.onPasswordChange(password)
        }

        registerConfirmPasswordEditText.onTextChanged { confirmPassword ->
            presenter.onConfirmPasswordChange(confirmPassword)
        }

        registerUsernameEditText.onTextChanged { username ->
            presenter.onUsernameChange(username)
        }
    }

    private fun setVisibilityProgressBar(visibility: Int){
        registerProgressBar.visibility = visibility
    }
}
