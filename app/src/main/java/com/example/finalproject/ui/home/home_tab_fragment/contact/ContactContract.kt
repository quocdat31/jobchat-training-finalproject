package com.example.finalproject.ui.home.home_tab_fragment.contact

import com.example.finalproject.base.BasePresenter
import com.example.finalproject.base.BaseView
import java.lang.Exception

interface ContactContract {

    interface View : BaseView {

        fun onAddFriendButtonClick()

        fun onAddFriendSuccess()

        fun onAddFriendError(exception: Exception)

        fun onSearchInputError()

    }

    interface Presenter : BasePresenter<View> {

        fun addFriend()

        fun onSearchInputChange()

    }


}