package com.example.android_programming

import android.app.Application
import com.example.android_programming.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : Application() {
    val database by lazy { AppDatabase.getInstance(this)}

    override fun onCreate() {
        super.onCreate()
//        this.deleteDatabase("my-db")
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.populateDatabase()
        }
    }
}