package com.example.android_programming

import android.content.Context
import com.example.android_programming.api.BackendService
import com.example.android_programming.api.repository.RestSneakerRepository
import com.example.android_programming.api.repository.RestUserRepository
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.database.repository.RemoteKeysRepositoryImpl
import com.example.android_programming.database.repository.BasketRepoImpl
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.database.repository.OrderRepoImpl
import com.example.android_programming.businessLogic.repo.OrderRepository
import com.example.android_programming.database.repository.SneakerRepoImpl

class AppDataContainer(private val context: Context) : AppContainer {
    override val sneakerRepo: RestSneakerRepository by lazy {
        RestSneakerRepository(
            BackendService.getInstance(),
            sneakerRepository,
            AppDatabase.getInstance(context),
            remoteKeyRepository
        )
    }
    override val userRepo: RestUserRepository by lazy {
        RestUserRepository(BackendService.getInstance())
    }
    override val orderRepo: OrderRepository by lazy {
        OrderRepoImpl(AppDatabase.getInstance(context).orderDao())
    }
    override val basketRepo: BasketRepository by lazy {
        BasketRepoImpl(AppDatabase.getInstance(context).basketDao())
    }
    private val sneakerRepository: SneakerRepoImpl by lazy {
        SneakerRepoImpl(AppDatabase.getInstance(context).sneakerDao())
    }
    private val remoteKeyRepository: RemoteKeysRepositoryImpl by lazy {
        RemoteKeysRepositoryImpl(AppDatabase.getInstance(context).remoteKeysDao())
    }
}