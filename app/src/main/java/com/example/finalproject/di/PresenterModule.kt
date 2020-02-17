package com.example.finalproject.di

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.ui.register.RegisterContract
import com.example.finalproject.ui.register.RegisterPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [InteractionModule::class])
class PresenterModule {

    @Provides
    fun providePresenter(firebaseAuthInterface: FirebaseAuthInterface): RegisterContract.Presenter {
        return RegisterPresenter(firebaseAuthInterface)
    }

}
