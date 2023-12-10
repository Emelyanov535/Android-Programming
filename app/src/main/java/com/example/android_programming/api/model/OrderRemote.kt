package com.example.android_programming.api.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.android_programming.model.Order
import com.example.android_programming.model.Sneaker
import kotlinx.serialization.Serializable

@Serializable
data class OrderRemote(
    val id: Int? = 0,
    val date: Long = 0L,
    val city: String = "",
    val street: String = "",
    val house: String = "",
    val subtotal: Double = 0.0,
    val taxes: Double = 0.0,
    val total: Double = 0.0,
    val userId: Int = 0
)

fun OrderRemote.toOrder(): Order = Order(
    id,
    date,
    city,
    street,
    house,
    subtotal,
    taxes,
    total,
    userId
)

fun Order.toOrderRemote():OrderRemote = OrderRemote(
    orderId,
    date,
    city,
    street,
    house,
    subtotal,
    taxes,
    total,
    creatorUserId
)