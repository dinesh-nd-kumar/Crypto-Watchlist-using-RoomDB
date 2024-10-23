package com.dineshdk.cryptowatchlist.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dineshdk.cryptowatchlist.repo.CurrencyRepo
import kotlinx.coroutines.launch

class WatchListViewModel:ViewModel() {


    private val repo = CurrencyRepo()


    fun loadData(context: Context) {
        viewModelScope.launch{
            repo.loadData(context)
        }
    }


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