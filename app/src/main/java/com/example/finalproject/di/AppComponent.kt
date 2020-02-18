package com.example.finalproject.di

import com.example.finalproject.ui.home.HomeContract
import com.example.finalproject.ui.register.RegisterContract
import com.example.finalproject.ui.register.login.LogInContract
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresenterModule::class])
@Singleton
interface AppComponent {

    fun registerPresenter(): RegisterContract.Presenter

    fun loginPresenter(): LogInContract.Presenter

    fun homePresenter(): HomeContract.Presenter
}
