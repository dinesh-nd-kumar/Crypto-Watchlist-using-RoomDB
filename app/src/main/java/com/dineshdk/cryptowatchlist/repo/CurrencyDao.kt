package com.dineshdk.cryptowatchlist.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dineshdk.cryptowatchlist.model.Currency

@Dao
interface CurrencyDao {

    // Insert a currency into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: Currency)

    // Insert a list of currency
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currency : List<Currency>)

    // Fetch all currency from the database
    @Query("SELECT * FROM currencies")
    suspend fun getAllCurrencies(): List<Currency>

    // Fetch a currency by ID
    @Query("SELECT * FROM currencies WHERE id = :currencyId")
    suspend fun getCurrencyById(currencyId: String): Currency?

    // Delete a currency by ID
    @Query("DELETE FROM currencies WHERE id = :currencyId")
    suspend fun deleteCurrencyById(currencyId: String)

    // Delete all currency
    @Query("DELETE FROM currencies")
    suspend fun deleteAllCurrencies()



}