package com.example.android_programming.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.OrderWithSneakers
import com.example.android_programming.model.UserWithOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert
    suspend fun createOrder(order: Order): Long

    @Insert
    suspend fun insertOrderSneaker(orderSneaker: OrderSneaker)

    @Query("SELECT * FROM 'Order' WHERE orderId = :id")
    fun getOrderWithSneakers(id: Int): Flow<OrderWithSneakers>

    @Query("SELECT * FROM `Order`")
    fun getAllOrder(): Flow<List<Order>>

    @Delete
    suspend fun delete(order: Order)

    @Query("SELECT * FROM users WHERE userId =:id")
    fun getUserOrders(id: Int) : Flow<UserWithOrder>
}