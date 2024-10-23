package com.dineshdk.cryptowatchlist.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dineshdk.cryptowatchlist.repo.CurrencyRepo
import kotlinx.coroutines.launch

class WatchListViewModel:ViewModel() {


    // All the  required live data are declared in the repo;
    // The reference of repo is declared as field in this viewmodel;
    // It also a best practice of MVVM pattern.
    private val repo = CurrencyRepo()


    //load data method is called onStart of the Activity
    fun loadData(context: Context) {
        viewModelScope.launch{
            repo.loadData(context)
        }
    }


    // no need for thread or coroutine for just get the data.
    fun getINRCurrencyLiveData() : LiveData<List<Currency>> {
        return repo.getINRData()
    }

    fun getUSDTCurrencyLiveData() : LiveData<List<Currency>> {
        return repo.getUSDTData()
    }

    fun getUPROCurrencyLiveData() : LiveData<List<Currency>> {
        return repo.getUPROData()
    }

    fun getAllFavoriteCurrencyLiveData() : LiveData<List<Currency>> {
        return repo.getAllFavoriteCurrency()
    }

    fun updateCurrencyToFavorite(c:Currency)  {
        viewModelScope.launch {
            repo.updateCurrency(c)
        }
    }
}