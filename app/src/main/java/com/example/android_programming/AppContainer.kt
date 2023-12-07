package com.example.android_programming

import com.example.android_programming.api.repository.RestSneakerRepository
import com.example.android_programming.api.repository.RestUserRepository
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.businessLogic.repo.OrderRepository

interface AppContainer {
    val sneakerRepo: RestSneakerRepository
    val userRepo: RestUserRepository
    val orderRepo: OrderRepository
    val basketRepo: BasketRepository

    companion object {
        const val TIMEOUT = 5000L
        const val LIMIT = 10
    }
}