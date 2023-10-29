package com.example.android_programming.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class OrderWithSneakers(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "sneakerId",
        associateBy = Junction(OrderSneaker::class)
    )
    val sneakers: List<Sneaker>
)


