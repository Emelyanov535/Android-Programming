package com.example.android_programming.api.model

import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.Sneaker
import kotlinx.serialization.Serializable

@Serializable
data class BasketSneakerRemote (
    val basketId: Int = 0,
    val sneakerId: Int = 0,
    val quantity: Int = 0
)

fun BasketSneakerRemote.toBasketSneaker(): BasketSneakers = BasketSneakers(
    basketId,
    sneakerId,
    quantity
)

fun BasketSneakers.toBasketSneakerRemote():BasketSneakerRemote = BasketSneakerRemote(
    basketId,
    sneakerId,
    quantity
)