package com.example.android_programming.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class BasketWithSneakers (
    @Embedded val basket: Basket,
    @Relation(
        parentColumn = "basketId",
        entityColumn = "sneakerId",
        associateBy = Junction(BasketSneakers::class)
    )
    val sneakers: List<Sneaker>
)