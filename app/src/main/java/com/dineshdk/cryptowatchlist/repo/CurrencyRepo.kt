package com.dineshdk.cryptowatchlist.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dineshdk.cryptowatchlist.R
import com.dineshdk.cryptowatchlist.model.Currency

class CurrencyRepo {

    private var inrCurrencyLiveData = MutableLiveData<List<Currency>>()
    private var usdtCurrencyLiveData = MutableLiveData<List<Currency>>()
    private var uproCurrencyLiveData = MutableLiveData<List<Currency>>()
    private var favoriteCurrencyLiveData = MutableLiveData<List<Currency>>()

    var context: Context? = null



    suspend fun loadData(context: Context) {
        this.context = context
        getAllCurrenciesFromRoom()
        inrCurrencyLiveData.postValue( getAllINRFromRoom())
        usdtCurrencyLiveData.postValue( getAllUSDTFromRoom())
        uproCurrencyLiveData.postValue( getAllUPROFromRoom())
        favoriteCurrencyLiveData.postValue(getFavFromRoom())


//        fetchProducts()
    }



    fun getINRData(): MutableLiveData<List<Currency>> {
        return inrCurrencyLiveData
    }
    fun getUSDTData(): MutableLiveData<List<Currency>> {
        return usdtCurrencyLiveData
    }
    fun getUPROData(): MutableLiveData<List<Currency>> {
        return uproCurrencyLiveData
    }
    fun getAllFavoriteCurrency() : MutableLiveData<List<Currency>>{
        return favoriteCurrencyLiveData
    }
    suspend fun updateCurrency(c: Currency){
        updateCurrencyToFavInRoom(c)
        favoriteCurrencyLiveData.postValue(getFavFromRoom())
    }




    private suspend fun getAllCurrenciesFromRoom(): List<Currency>? {


        var listfromroom:List<Currency> = listOf()

        DatabaseBuilder.run {
            context?.let {
                listfromroom = getInstance(it).productDao().getAllCurrencies()
                if (listfromroom.isEmpty()){
                    val list = getList()
                    getInstance(it).productDao().insertCurrencies(list)
                    listfromroom = getInstance(it).productDao().getAllCurrencies()
                }

            }
        }
        return listfromroom


    }

    private suspend fun getFavFromRoom(): List<Currency>? {
        var favListFromRoom:List<Currency> = listOf()
        DatabaseBuilder.run {
            context?.let {
                favListFromRoom = getInstance(it).productDao().getAllFavoriteCurrency()

            }
        }
        return favListFromRoom

    }

    private suspend fun getAllINRFromRoom(): List<Currency>? {
        var list:List<Currency> = listOf()
        DatabaseBuilder.run {
            context?.let {
                list = getInstance(it).productDao().getAllCurrencyByType("INR")

            }
        }
        return list

    }

    private suspend fun getAllUSDTFromRoom(): List<Currency>? {
        var list:List<Currency> = listOf()
        DatabaseBuilder.run {
            context?.let {
                list = getInstance(it).productDao().getAllCurrencyByType("USDT")

            }
        }
        return list

    }

    private suspend fun getAllUPROFromRoom(): List<Currency>? {
        var list:List<Currency> = listOf()
        DatabaseBuilder.run {
            context?.let {
                list = getInstance(it).productDao().getAllCurrencyByType("UPRO")

            }
        }
        return list

    }

     private suspend fun updateCurrencyToFavInRoom(c :Currency) {
        DatabaseBuilder.run {
            context?.let {
                getInstance(it).productDao().updateCurrency(c)

            }
        }

    }


    private fun getList() = listOf(
        Currency("1rwt","BTC/USDT","USDT","ss", R.drawable.iq,1,51254.27f,true,0.63f),
        Currency("1werg","Walton/USDT","USDT","ss",R.drawable.upro,2,212564.27f,true,-1.3f),
        Currency("1erd","Dash / INR","INR","ss", R.drawable.pepe,3,21564.27f,false,1.3f),
        Currency("1s,d","NANO / UPRO","UPRO","ss",R.drawable.upro,1,212564.27f,false,-0.3f),
        Currency("1fxb","BTC/USDT","USDT","ss",R.drawable.ape,3,24564.27f,false,-0.55f),
        Currency("1fd3f","NANO / INR","INR","ss",R.drawable.bitcion,2,31564.27f,false,0.413f),
        Currency("1fdse3f","NANO / UPRO","UPRO","ss",R.drawable.cat,2,31564.27f,false,0.413f),
        Currency("1avvd","Dash / INR","INR","ss",R.drawable.dogd,3,218564.27f,false,-0.3f),
        Currency("1sds","Dash / USDT","USDT","ss", R.drawable.bitcion,1,32164.27f,false,-0.963f),
        Currency("1df","XZC/ INR","INR","ss", R.drawable.dogd,5,14456.27f,false,0.3f),
        Currency("1ssdh","BTC / USDT","USDT","ss", R.drawable.simpa,2,32154.27f,false,-2.3f),
        Currency("1sdth","BTC / UPRO","UPRO","ss",R.drawable.maga,2,32154.27f,false,-2.3f),
        Currency("1gtdh","BTC / USDT","USDT","ss", R.drawable.pepe,2,32154.27f,false,-2.3f),
        )








}
