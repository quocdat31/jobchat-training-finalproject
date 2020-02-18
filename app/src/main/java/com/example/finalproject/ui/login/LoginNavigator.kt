package com.example.finalproject.ui.login

import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.ui.home.MainActivity
import com.example.finalproject.base.BaseNavigator
import com.example.finalproject.ui.register.RegisterActivity

class LoginNavigator(activity: AppCompatActivity) : BaseNavigator(activity),
    LogInContract.Navigator {
    override fun navigateHomeScreen() {
        activity.startActivity(MainActivity.getInstance(activity))
    }

    override fun navigateSignUpScreen() {
        activity.startActivity(RegisterActivity.getInstance(activity))
    }

}
