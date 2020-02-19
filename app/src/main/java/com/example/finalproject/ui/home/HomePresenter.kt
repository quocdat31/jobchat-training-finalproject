package com.example.finalproject.ui.home

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface

class HomePresenter constructor(private val firebaseAuthInterface: FirebaseAuthInterface): HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    override fun onLogOutButtonClick() {
        firebaseAuthInterface.logout {
            view.onLogOut()
        }
    }

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun onStart() = Unit

    override fun onStop() = Unit

}