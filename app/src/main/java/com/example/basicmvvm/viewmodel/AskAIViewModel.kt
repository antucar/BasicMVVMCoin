package com.example.basicmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.basicmvvm.model.ChatbotResponseEntity
import com.example.basicmvvm.model.CoinModel
import com.example.basicmvvm.model.MessageModel
import com.example.basicmvvm.service.ChatBotServiceApi
import com.example.basicmvvm.service.CoinApiService
import com.example.basicmvvm.util.CustomSharedPreferences
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import viewmodel.BaseViewModel

class AskAIViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())
    private val chatbotservice = ChatBotServiceApi()

    val messageinmodel = MutableLiveData<ChatbotResponseEntity>()

    fun giveCoin(usermessage: String): String {
    return chatbotservice.getChatbotResponse(usermessage)
    }

    fun getData(): String {
       return messageinmodel.value?.chatbotResponse.toString()
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}