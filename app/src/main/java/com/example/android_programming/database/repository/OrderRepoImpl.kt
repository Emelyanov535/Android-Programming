//package com.example.android_programming.database.repository
//
//import com.example.android_programming.businessLogic.repo.OrderRepository
//import com.example.android_programming.database.dao.OrderDao
//import com.example.android_programming.model.Order
//import com.example.android_programming.model.OrderSneaker
//import com.example.android_programming.model.OrderWithSneakers
//import com.example.android_programming.model.Sneaker
//import com.example.android_programming.model.UserWithOrder
//import kotlinx.coroutines.flow.Flow
//
//class OrderRepoImpl(private val orderDao: OrderDao) : OrderRepository {
//
//    override suspend fun createOrder(order: Order): Long = orderDao.createOrder(order)
//
//    override suspend fun insertOrderSneaker(orderSneaker: OrderSneaker) = orderDao.insertOrderSneaker(orderSneaker)
//    override suspend fun getSneakerFromOrder(id: Int): Flow<List<Sneaker>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getUserOrders(id: Int): Flow<List<Order>> {
//        TODO("Not yet implemented")
//    }
//
////    override suspend fun delete(order: Order) = orderDao.delete(order)
////
////    override fun getOrderWithSneakers(id: Int): Flow<OrderWithSneakers> = orderDao.getOrderWithSneakers(id)
////
////    override fun getAllOrder(): Flow<List<Order>> = orderDao.getAllOrder()
////
////    override fun getUserOrders(id: Int): Flow<UserWithOrder> = orderDao.getUserOrders(id)
//}