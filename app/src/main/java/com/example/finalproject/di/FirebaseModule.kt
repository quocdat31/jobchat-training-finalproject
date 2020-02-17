package com.example.finalproject.di

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class FirebaseModule {

    @Provides
    fun firebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}
