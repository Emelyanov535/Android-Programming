package com.example.android_programming.di

import android.content.Context
import com.example.android_programming.api.BackendService
import com.example.android_programming.api.repository.RestBasketRepository
import com.example.android_programming.api.repository.RestOrderRepository
import com.example.android_programming.api.repository.RestReportRepository
import com.example.android_programming.api.repository.RestSneakerRepository
import com.example.android_programming.api.repository.RestUserRepository
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.database.repository.RemoteKeysRepositoryImpl
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.businessLogic.repo.OrderRepository
import com.example.android_programming.businessLogic.repo.ReportRepository
import com.example.android_programming.businessLogic.repo.SneakerRepository
import com.example.android_programming.businessLogic.repo.UserRepository
import com.example.android_programming.database.repository.SneakerRepoImpl

class AppDataContainer(private val context: Context) : AppContainer {
    override val sneakerRepo: SneakerRepository by lazy {
        RestSneakerRepository(
            BackendService.getInstance(),
            sneakerRepository,
            AppDatabase.getInstance(context),
            remoteKeyRepository
        )
    }
    override val userRepo: UserRepository by lazy {
        RestUserRepository(BackendService.getInstance())
    }
    override val basketRepo: BasketRepository by lazy {
        RestBasketRepository(BackendService.getInstance())
    }
    override val reportRepo: ReportRepository by lazy {
        RestReportRepository()
    }
    override val orderRepo: OrderRepository by lazy {
        RestOrderRepository(BackendService.getInstance())
    }
    private val sneakerRepository: SneakerRepoImpl by lazy {
        SneakerRepoImpl(AppDatabase.getInstance(context).sneakerDao())
    }
    private val remoteKeyRepository: RemoteKeysRepositoryImpl by lazy {
        RemoteKeysRepositoryImpl(AppDatabase.getInstance(context).remoteKeysDao())
    }
}