package com.dineshdk.cryptowatchlist.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dineshdk.cryptowatchlist.model.Currency

@Database(entities = [Currency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): CurrencyDao
}