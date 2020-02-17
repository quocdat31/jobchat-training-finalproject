package com.example.finalproject.di

import com.example.finalproject.ui.register.RegisterContract
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresenterModule::class])
@Singleton
interface AppComponent {

    fun registerPresenter(): RegisterContract.Presenter

}
