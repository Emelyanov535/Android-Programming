package com.example.android_programming.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int? = null,
    @ColumnInfo(name = "Date")
    val date: Long,
    @ColumnInfo(name = "City")
    val city: String,
    @ColumnInfo(name = "Street")
    val street: String,
    @ColumnInfo(name = "House")
    val house: String,
    @ColumnInfo(name = "Sub Total")
    val subtotal: Double,
    @ColumnInfo(name = "Taxes")
    val taxes: Double,
    @ColumnInfo(name = "Total")
    val total: Double,
    @ColumnInfo(name = "CreatorUserId")
    val creatorUserId: Int
)
