package com.example.finalproject.di

import com.example.finalproject.firebase.authentication.FirebaseAuthImp
import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.firebase.database.FirebaseDatabaseImp
import com.example.finalproject.firebase.database.FirebaseDatabaseInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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

    @Provides
    fun database(firebaseDatabase: FirebaseDatabase): FirebaseDatabaseInterface {
        return FirebaseDatabaseImp(firebaseDatabase)
    }

}
