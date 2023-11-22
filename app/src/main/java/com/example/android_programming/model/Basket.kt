package com.example.android_programming.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Basket (
    @PrimaryKey
    val basketId: Int? = null,
    @ColumnInfo(name = "CreatorUserId")
    val creatorUserId: Int
)