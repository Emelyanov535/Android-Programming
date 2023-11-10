package com.example.android_programming.repository

import com.example.android_programming.dao.OrderDao
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.OrderWithSneakers
import kotlinx.coroutines.flow.Flow

class OrderRepoImpl(private val orderDao: OrderDao) : OrderRepository {

    override suspend fun createOrder(order: Order): Long = orderDao.createOrder(order)

    override suspend fun insertOrderSneaker(orderSneaker: OrderSneaker) = orderDao.insertOrderSneaker(orderSneaker)

    override suspend fun delete(order: Order) = orderDao.delete(order)

    override fun getOrderWithSneakers(id: Int): Flow<OrderWithSneakers> = orderDao.getOrderWithSneakers(id)

    override fun getAllOrder(): Flow<List<Order>> = orderDao.getAllOrder()
}