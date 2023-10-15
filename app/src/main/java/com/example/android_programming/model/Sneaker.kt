package com.example.android_programming.model

import androidx.compose.runtime.MutableState
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sneakers")
data class Sneaker(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "Brand")
    val brand: String,
    @ColumnInfo(name = "Model")
    val model: String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Price")
    val price: Double,
    @ColumnInfo(name = "Photo")
    val photo: Int
)
