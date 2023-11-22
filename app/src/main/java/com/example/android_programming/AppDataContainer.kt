package com.example.android_programming

import android.content.Context
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.repository.BasketRepoImpl
import com.example.android_programming.repository.BasketRepository
import com.example.android_programming.repository.OrderRepoImpl
import com.example.android_programming.repository.OrderRepository
import com.example.android_programming.repository.SneakerRepoImpl
import com.example.android_programming.repository.SneakerRepository
import com.example.android_programming.repository.UserRepoImpl
import com.example.android_programming.repository.UserRepository

class AppDataContainer(private val context: Context) : AppContainer {
    override val sneakerRepo: SneakerRepository by lazy {
        SneakerRepoImpl(AppDatabase.getInstance(context).sneakerDao())
    }
    override val userRepo: UserRepository by lazy {
        UserRepoImpl(AppDatabase.getInstance(context).userDao())
    }
    override val orderRepo: OrderRepository by lazy {
        OrderRepoImpl(AppDatabase.getInstance(context).orderDao())
    }
    override val basketRepo: BasketRepository by lazy {
        BasketRepoImpl(AppDatabase.getInstance(context).basketDao())
    }
}