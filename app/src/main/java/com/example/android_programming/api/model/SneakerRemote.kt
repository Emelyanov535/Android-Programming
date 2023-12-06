package com.example.android_programming.api.model

import com.example.android_programming.model.Sneaker
import kotlinx.serialization.Serializable

@Serializable
data class SneakerRemote (
    val id: Int? = 0,
    val brand: String = "",
    val model: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val photo: Int = 0
)

fun SneakerRemote.toSneaker():Sneaker = Sneaker(
    id,
    brand,
    model,
    description,
    price,
    photo
)

fun Sneaker.toSneakerRemote():SneakerRemote = SneakerRemote(
    sneakerId,
    brand,
    model,
    description,
    price,
    photo
)