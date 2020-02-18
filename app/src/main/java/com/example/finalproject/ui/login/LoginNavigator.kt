package com.example.finalproject.ui.login

import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.ui.home.HomeActivity
import com.example.finalproject.base.BaseNavigator
import com.example.finalproject.ui.register.RegisterActivity
import com.example.finalproject.ui.register.login.LogInContract

class LoginNavigator(activity: AppCompatActivity) : BaseNavigator(activity),
    LogInContract.Navigator {
    override fun navigateHomeScreen() {
        activity.startActivity(HomeActivity.getInstance(activity))
    }

    override fun navigateSignUpScreen() {
        activity.startActivity(RegisterActivity.getInstance(activity))
    }

}
