package com.example.android_programming.model

import androidx.room.Entity

@Entity(primaryKeys = ["orderId", "sneakerId"])
data class OrderSneaker(
    val orderId: Int,
    val sneakerId: Int,
    val quantity: Int
)