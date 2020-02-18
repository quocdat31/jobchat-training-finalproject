package com.example.finalproject.ui.register

import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.base.BaseNavigator
import com.example.finalproject.ui.home.MainActivity

class RegisterNavigator(activity: AppCompatActivity) : BaseNavigator(activity),
    RegisterContract.Navigator {
    override fun navigateMainScreen() {
        activity.startActivity(MainActivity.getInstance(activity))
    }
}
