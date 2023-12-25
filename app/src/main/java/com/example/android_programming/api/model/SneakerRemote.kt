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
    val photo: String = ""
)

fun SneakerRemote.toSneaker():Sneaker = Sneaker(
    id,
    brand,
    model,
    description,
    price,
    RemoteConverters.toBitmap(photo)
)

fun Sneaker.toSneakerRemote():SneakerRemote = SneakerRemote(
    sneakerId,
    brand,
    model,
    description,
    price,
    RemoteConverters.fromBitmap(photo)
)