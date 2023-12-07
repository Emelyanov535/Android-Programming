package com.example.android_programming

import android.app.Application
import com.example.android_programming.di.AppContainer
import com.example.android_programming.di.AppDataContainer

class App : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}