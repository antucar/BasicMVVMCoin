package com.example.basicmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.basicmvvm.model.CoinModel
import com.example.basicmvvm.service.CoinApiService
import com.example.basicmvvm.util.CustomSharedPreferences
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import viewmodel.BaseViewModel

class CoinDetailViewModel (application: Application): BaseViewModel(application)  {


    private val coinApiservice = CoinApiService()
    private val disposable = CompositeDisposable()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())
    val coins = MutableLiveData<List<CoinModel>>()

    fun refreshData(){
        getDataFromApi()
    }

    private fun getDataFromApi() {
        disposable.add(
            coinApiservice.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CoinModel>>() {
                    override fun onSuccess(t: List<CoinModel>) {
                        coins.value = t
                        if (t.isNotEmpty()) {
                            coins.value =
                                listOf(t[0]) // Assuming you want the first coin in the list
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}