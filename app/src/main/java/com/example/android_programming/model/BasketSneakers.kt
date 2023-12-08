package com.example.android_programming.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["basketId", "sneakerId"])
class BasketSneakers(
    val basketId: Int,
    val sneakerId: Int,
    val quantity: Int
)