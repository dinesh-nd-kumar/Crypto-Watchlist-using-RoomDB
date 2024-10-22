package com.dineshdk.cryptowatchlist.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dineshdk.cryptowatchlist.model.Currency

class CurrencyRepo {

    private var currencyLiveData = MutableLiveData<List<Currency>>()

    var context: Context? = null





    suspend fun loadData(context: Context) {
        this.context = context
        currencyLiveData.postValue( getProductFromRoom())
//        fetchProducts()
    }

    private fun getProductFromRoom(): List<Currency>? {
        return listOf(
            Currency("1rwt","ss","1","ss","ee",1,3),
            Currency("1werg","ss","1","ss","ee",1,3),
            Currency("1erd","ss","1","ss","ee",1,3),
            Currency("1s,d","ss","1","ss","ee",1,3),
            Currency("1fxb","ss","1","ss","ee",1,3),
            Currency("1fdf","ss","1","ss","ee",1,3),
            Currency("1avvd","ss","1","ss","ee",1,3),
            Currency("1sds","ss","1","ss","ee",1,3),
            Currency("1df","ss","1","ss","ee",1,3),
            Currency("1sdh","ss","1","ss","ee",1,3),

            )
    }


    fun getData(): MutableLiveData<List<Currency>> {
        return currencyLiveData
    }







}
