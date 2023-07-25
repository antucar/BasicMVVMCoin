package com.example.basicmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.basicmvvm.model.CoinModel
import com.example.basicmvvm.service.CoinApiService
import com.example.basicmvvm.service.CoinDatabase
import com.example.basicmvvm.util.CustomSharedPreferences
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import viewmodel.BaseViewModel

class CoinDetailViewModel (application: Application): BaseViewModel(application)  {


    private val coinApiservice = CoinApiService()
    private val disposable = CompositeDisposable()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())
    val coin = MutableLiveData<CoinModel>()
    val selectedCoin = MutableLiveData<CoinModel>()
//    fun refreshData(){
//        getDataFromApi()
//    }

    fun getDataFromApi(uuid: Int) {
        disposable.add(
            coinApiservice.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CoinModel>>() {
                    override fun onSuccess(t: List<CoinModel>) {
                        // Assuming that t contains the list of coins from the API response
                        coin.value = t.find { it.uuid == uuid }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }


    fun getDataFromRoom(uuid: Int) {
        launch {
            val dao = CoinDatabase(getApplication()).coinDao()
            val coin = dao.getCoins(uuid)
            selectedCoin.value = coin
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}