package com.example.basicmvvm.viewmodel

import android.app.Application
import com.example.basicmvvm.service.CoinApiService
import com.example.basicmvvm.util.CustomSharedPreferences
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import viewmodel.BaseViewModel

class AskAIViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())
    private val coinApiservice = CoinApiService()

    fun postApi() {


    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}