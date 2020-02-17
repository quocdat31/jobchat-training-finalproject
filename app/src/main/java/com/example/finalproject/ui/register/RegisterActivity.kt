package com.example.finalproject.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R
import com.example.finalproject.registerPresenter
import com.example.finalproject.ultis.onTextChanged
import com.example.finalproject.ultis.toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private val presenter by lazy { registerPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter.setView(this)

//        presenter.apply {
//            onEmailChange("qdat09.1@gmail.com")
//            onPasswordChange("dat123456")
//            onConfirmPasswordChange("dat123456")
//            onUsernameChange("dat")
//            onSubmitRegister()
//        }

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

    override fun onRegisterSuccess() {
        this.toast("Success, ${FirebaseAuth.getInstance().currentUser.toString()}")
    }

    override fun onRegisterError(e: Exception?) {
        this.toast(e?.message.toString())
    }

    override fun showPasswordError() {
        registerPasswordEditText.error = getString(R.string.passwordInputError)
    }

    override fun showEmailError() {
        registerEmailEditText.error = getString(R.string.emailInputError)
    }

    override fun showPasswordMatchingError() {
        registerConfirmPasswordEditText.error = getString(R.string.confirmPasswordInputError)
    }


}
