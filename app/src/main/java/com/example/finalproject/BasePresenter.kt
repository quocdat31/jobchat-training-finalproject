package com.example.finalproject

interface BasePresenter<in T> {
    fun setView(view: T)
    fun onStart()
    fun onStop()
}
