package com.example.finalproject.di

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.ui.home.HomeContract
import com.example.finalproject.ui.home.HomePresenter
import com.example.finalproject.ui.register.RegisterContract
import com.example.finalproject.ui.register.RegisterPresenter
import com.example.finalproject.ui.register.login.LogInContract
import com.example.finalproject.ui.register.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [InteractionModule::class])
class PresenterModule {

    @Provides
    fun providePresenter(firebaseAuthInterface: FirebaseAuthInterface): RegisterContract.Presenter {
        return RegisterPresenter(firebaseAuthInterface)
    }

    @Provides
    fun provideLoginPresenter(firebaseAuthInterface: FirebaseAuthInterface): LogInContract.Presenter {
        return LoginPresenter(firebaseAuthInterface)
    }

    @Provides
    fun provideHomePresenter(firebaseAuthInterface: FirebaseAuthInterface): HomeContract.Presenter {
        return HomePresenter(firebaseAuthInterface)
    }
}
