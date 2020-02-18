package com.example.finalproject.ui.home

import com.example.finalproject.base.BasePresenter
import com.example.finalproject.base.BaseView

interface HomeContract {

    interface View : BaseView {
        fun onLogOut()
    }

    interface Presenter : BasePresenter<View> {
        fun logOut()
    }

    interface Navigator {
        fun navigateLoginScreen()
    }

}
