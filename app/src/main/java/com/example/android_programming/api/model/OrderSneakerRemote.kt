package com.example.android_programming.api.model

import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.OrderSneaker
import kotlinx.serialization.Serializable

@Serializable
data class OrderSneakerRemote (
    val orderId: Int = 0,
    val sneakerId: Int = 0,
    val quantity: Int = 0
)

fun OrderSneakerRemote.toOrderSneaker(): OrderSneaker = OrderSneaker(
    orderId,
    sneakerId,
    quantity
)

fun OrderSneaker.toOrderSneakerRemote():OrderSneakerRemote = OrderSneakerRemote(
    orderId,
    sneakerId,
    quantity
)