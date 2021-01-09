package com.example.architecturebase.network

interface MVPContract {

    interface IView{

    }

    interface IPresenter{
        fun getPost()
    }
}