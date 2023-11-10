package com.example.android_programming.repository

import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.OrderWithSneakers
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun createOrder(order: Order): Long
    suspend fun insertOrderSneaker(orderSneaker: OrderSneaker)
    suspend fun delete(order: Order)
    fun getOrderWithSneakers(id: Int): Flow<OrderWithSneakers>
    fun getAllOrder(): Flow<List<Order>>
}