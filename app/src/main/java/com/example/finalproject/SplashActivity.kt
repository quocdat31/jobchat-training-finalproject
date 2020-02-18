package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.ui.home.MainActivity
import com.example.finalproject.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_MILI: Long = 500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent: Intent = if (FirebaseAuth.getInstance().currentUser == null) {
            Intent(LoginActivity.getInstance(this))
        } else Intent(MainActivity.getInstance(this))

        Handler().postDelayed(
            {
                run {
                    startActivity(intent)
                    finish()
                }
            }, DELAY_MILI
        )

    }
}
