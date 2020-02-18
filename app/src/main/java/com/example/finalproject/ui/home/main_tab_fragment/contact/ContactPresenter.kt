package com.example.finalproject.ui.home.main_tab_fragment.contact

import com.example.finalproject.firebase.authentication.FirebaseAuthInterface
import com.example.finalproject.firebase.database.FirebaseDatabaseInterface
import com.example.finalproject.model.Friend

class ContactPresenter constructor(
    private val firebaseDatabaseInterface: FirebaseDatabaseInterface,
    private val firebaseAuthInterface: FirebaseAuthInterface
) :
    ContactContract.Presenter {

    private lateinit var mView: ContactContract.View
    private var mFriend = Friend()

    override fun addFriend() {
        firebaseDatabaseInterface.addFriend(
            firebaseAuthInterface.getUserId(),
            mFriend.email.toString()
        ) { onResult, e ->
            if (onResult) mView.onAddFriendSuccess() else mView.onAddFriendError(e)
        }
    }

    override fun onSearchInputChange(email: String) {
        mFriend.email = email
    }

    override fun getCurrentUserEmail(): String = firebaseAuthInterface.getUserEmail()

    override fun getCurrentUserId(): String = firebaseAuthInterface.getUserId()

    override fun getContactList(id: String) {
        firebaseDatabaseInterface.getFriendList(getCurrentUserId()) { contactList, databaseError ->
            if (databaseError?.message?.length != null) {
                mView.onGetContactError(databaseError)
            } else mView.onGetContactSuccess(contactList as ArrayList<Friend>)
        }
    }

    override fun setView(view: ContactContract.View) {
        this.mView = view
    }

    override fun onStart() = Unit

    override fun onStop() = Unit
}
