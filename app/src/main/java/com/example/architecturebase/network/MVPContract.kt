package com.example.architecturebase.network

interface MVPContract {

    interface IView{
        fun showPost()
    }

    interface IPresenter{
        fun getPost()
    }
}