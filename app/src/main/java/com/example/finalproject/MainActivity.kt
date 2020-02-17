package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.finalproject.ui.register.RegisterActivity

class MainActivity : AppCompatActivity() {

    private val DELAY_MILI: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, RegisterActivity::class.java)

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
