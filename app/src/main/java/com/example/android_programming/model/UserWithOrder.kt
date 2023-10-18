package com.example.android_programming.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithOrder(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "CreatorUserId"
    )
    val orders: List<Order>
)