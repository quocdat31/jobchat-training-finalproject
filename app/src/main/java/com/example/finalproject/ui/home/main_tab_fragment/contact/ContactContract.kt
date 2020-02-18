package com.example.finalproject.ui.home.main_tab_fragment.contact

import com.example.finalproject.base.BasePresenter
import com.example.finalproject.base.BaseView
import com.example.finalproject.model.Friend
import com.google.firebase.database.DatabaseError

interface ContactContract {
    interface View : BaseView {
        fun onAddFriendButtonClick()
        fun onAddFriendSuccess()
        fun onAddFriendError(exception: String?)
        fun onSearchInputError()
        fun onGetContactSuccess(friendList: ArrayList<Friend>)
        fun onGetContactError(databaseError: DatabaseError?)
    }
    interface Presenter : BasePresenter<View> {
        fun addFriend()
        fun onSearchInputChange(email: String)
        fun getCurrentUserEmail(): String
        fun getCurrentUserId(): String
        fun getContactList(id: String)
    }
}
