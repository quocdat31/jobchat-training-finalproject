package com.example.finalproject.ui.home.home_tab_fragment.contact

import com.example.finalproject.firebase.database.FirebaseDatabaseInterface
import com.example.finalproject.model.Friend

class ContactPresenter constructor(private val firebaseDatabaseInterface: FirebaseDatabaseInterface) :
    ContactContract.Presenter {

    private var friend = Friend()

    override fun addFriend() {
//        firebaseDatabaseInterface.addFriend()
    }

    override fun onSearchInputChange() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setView(view: ContactContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
