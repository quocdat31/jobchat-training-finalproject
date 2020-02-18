package com.example.finalproject.base

interface BasePresenter<in T> {
    fun setView(view: T)
    fun onStart()
    fun onStop()
}
