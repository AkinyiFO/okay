package com.ibrajix.directcurrencyconverter

import android.app.Application
import com.ibrajix.directcurrencyconverter.data.LogRoomDatabase

class LogApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database: LogRoomDatabase by lazy { LogRoomDatabase.getDatabase(this) }
}