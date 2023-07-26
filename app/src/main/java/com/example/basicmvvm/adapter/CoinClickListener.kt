package com.example.basicmvvm.adapter

import android.view.View
import com.example.basicmvvm.model.CoinModel

interface CoinClickListener {
    fun onCoinClicked(v: View)

    fun getFormattedDecimal(double: Double): String {
        val currencyValue = double.toString()
        if (double == null){
            return double
        }
        // Ondalık kısmı belirle
        val decimalIndex = currencyValue.indexOf('.')

        // Eğer ondalık kısm yoksa veya 3 basamağa kadar zaten varsa, olduğu gibi geri döndür
        if (decimalIndex == -1 || decimalIndex + 4 >= currencyValue.length) {
            return currencyValue
        }

        // Ondalık kısmı 3 basamağa düşür
        val truncatedDecimal = currencyValue.substring(0, decimalIndex + 4)

        return truncatedDecimal

    }

}