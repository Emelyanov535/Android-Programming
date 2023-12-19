package com.example.android_programming.di

import com.example.android_programming.api.repository.RestBasketRepository
import com.example.android_programming.api.repository.RestSneakerRepository
import com.example.android_programming.api.repository.RestUserRepository
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.businessLogic.repo.OrderRepository
import com.example.android_programming.businessLogic.repo.SneakerRepository
import com.example.android_programming.businessLogic.repo.UserRepository

interface AppContainer {
    val sneakerRepo: SneakerRepository
    val userRepo: UserRepository
    val orderRepo: OrderRepository
    val basketRepo: BasketRepository

    companion object {
        const val TIMEOUT = 500L
        const val LIMIT = 10
    }
}