package com.example.finalproject.ui.home

import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.base.BaseNavigator
import com.example.finalproject.ui.login.LoginActivity

class MainNavigator(activity: AppCompatActivity) : BaseNavigator(activity),
    HomeContract.Navigator {
    override fun navigateLoginScreen() {
        activity.startActivity(LoginActivity.getInstance(activity))
    }
}
