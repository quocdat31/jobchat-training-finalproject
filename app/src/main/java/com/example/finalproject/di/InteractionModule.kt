package com.example.finalproject.di

import com.example.finalproject.firebase.authentication.FirebaseAuthImp
import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [FirebaseModule::class])
@Singleton
class InteractionModule {

    @Provides
    fun authentication(firebaseAuth: FirebaseAuth): FirebaseAuthInterface {
        return FirebaseAuthImp(firebaseAuth)
    }

}
