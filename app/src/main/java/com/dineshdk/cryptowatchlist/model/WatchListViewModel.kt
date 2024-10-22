package com.dineshdk.cryptowatchlist.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dineshdk.cryptowatchlist.model.Currency
import com.dineshdk.cryptowatchlist.repo.CurrencyRepo
import kotlinx.coroutines.launch

class WatchListViewModel:ViewModel() {


    private val repo = CurrencyRepo()


    fun loadData(context: Context) {
        viewModelScope.launch{
            repo.loadData(context)
        }
    }



    fun getProductLiveData() : LiveData<List<Currency>> {
        return repo.getData()
    }
}