package com.example.android_programming.api.repository

import com.example.android_programming.api.BackendService
import com.example.android_programming.api.model.toOrder
import com.example.android_programming.api.model.toOrderRemote
import com.example.android_programming.api.model.toOrderSneakerRemote
import com.example.android_programming.api.model.toSneaker
import com.example.android_programming.businessLogic.repo.OrderRepository
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RestOrderRepository(private val service: BackendService): OrderRepository {
    override suspend fun createOrder(order: Order): Long {
        return service.createOrder(order.toOrderRemote())
    }

    override suspend fun insertOrderSneaker(orderSneaker: OrderSneaker) {
        service.createOrderSneaker(orderSneaker.toOrderSneakerRemote())
    }

    override suspend fun delete(orderId: Int) {
        service.deleteOrder(orderId)
    }

    override suspend fun getSneakerFromOrder(id: Int): Flow<List<Sneaker>> {
        val sneakersRemoteList = service.getSneakerFromOrder(id)
        val sneakersList = sneakersRemoteList.map { it.toSneaker() }
        return flowOf(sneakersList.toList())
    }

    override suspend fun getUserOrders(id: Int): Flow<List<Order>> {
        val ordersRemoteList = service.getUserOrders(id)
        val ordersList = ordersRemoteList.map { it.toOrder() }
        return flowOf(ordersList.toList())
    }
}