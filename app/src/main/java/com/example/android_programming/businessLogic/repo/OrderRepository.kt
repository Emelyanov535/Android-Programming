package com.example.android_programming.businessLogic.repo

import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.OrderWithSneakers
import com.example.android_programming.model.Sneaker
import com.example.android_programming.model.UserWithOrder
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun createOrder(order: Order): Long
    suspend fun insertOrderSneaker(orderSneaker: OrderSneaker)
    suspend fun delete(orderId: Int)
suspend fun getSneakerFromOrder(id: Int): Flow<List<Sneaker>>
//    fun getAllOrder(): Flow<List<Order>>
suspend fun getUserOrders(id: Int) : Flow<List<Order>>
}