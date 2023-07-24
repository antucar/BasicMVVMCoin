package viewmodel
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basicmvvm.model.CoinModel
import com.example.basicmvvm.service.CoinApiService
import com.example.basicmvvm.service.CoinDatabase
import com.example.basicmvvm.util.CustomSharedPreferences
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


class SecondFragmentViewModel(application: Application): BaseViewModel(application) {

    private val coinApiservice = CoinApiService()
    private val disposable = CompositeDisposable()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())
    val coins = MutableLiveData<List<CoinModel>>()
    val coinerror = MutableLiveData<Boolean>()
    val coinprogress = MutableLiveData<Boolean>()
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData(){
        val updateTime = customSharedPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime ) {
        getDataFromSQLite()
        } else {
            getDataFromApi()
        }

    }
    fun refreshFromApi(){
        getDataFromApi()
    }
    private fun getDataFromSQLite(){
        coinprogress.value = true
        launch {
            val coins = CoinDatabase(getApplication()).coinDao().getAllCoins()
            showCoins(coins)
//            Toast.makeText(getApplication(),"from sqlite",Toast.LENGTH_LONG).show()
        }
    }
    private fun getDataFromApi(){
        coinprogress.value = true
        disposable.add(
            coinApiservice.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CoinModel>>(){
                    override fun onSuccess(t: List<CoinModel>) {
                        storeInSQLite(t)
//                        Toast.makeText(getApplication(),"from api",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        coinerror.value = true
                        coinprogress.value = false
                        e.printStackTrace()
                    }

                })

        )
    }

    private fun showCoins(coinlist: List<CoinModel>){
        coins.value = coinlist
        coinerror.value = false
        coinprogress.value = false
    }

    private fun storeInSQLite(list: List<CoinModel>) {
    launch {
        val dao = CoinDatabase(getApplication()).coinDao()
        dao.deleteCoins()
        val listlong = dao.insertAll(*list.toTypedArray()) // individual
        var i = 0
        while (i < list.size ){
            list[i].uuid = listlong[i].toInt()
            i= i + 1
        }
    showCoins(list)


    }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}