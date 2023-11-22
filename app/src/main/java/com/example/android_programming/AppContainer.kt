package com.example.android_programming

import com.example.android_programming.repository.BasketRepository
import com.example.android_programming.repository.OrderRepository
import com.example.android_programming.repository.SneakerRepository
import com.example.android_programming.repository.UserRepository

interface AppContainer {
    val sneakerRepo: SneakerRepository
    val userRepo: UserRepository
    val orderRepo: OrderRepository
    val basketRepo: BasketRepository
}