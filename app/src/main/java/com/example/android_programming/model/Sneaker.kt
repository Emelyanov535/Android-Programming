package com.example.android_programming.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sneaker(
    @PrimaryKey(autoGenerate = true)
    val sneakerId: Int? = null,
    @ColumnInfo(name = "Brand")
    val brand: String,
    @ColumnInfo(name = "Model")
    val model: String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Price")
    val price: Double,
    @ColumnInfo(name = "Photo")
    val photo: Bitmap
)
