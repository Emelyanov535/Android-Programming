package com.example.android_programming

import android.app.Application
import com.example.android_programming.database.AppDatabase

public class App : Application() {
    val database by lazy { AppDatabase.getInstance(this)}
}