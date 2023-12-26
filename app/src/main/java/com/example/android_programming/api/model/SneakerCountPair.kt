package com.example.android_programming.api.model

import kotlinx.serialization.Serializable

@Serializable
data class SneakerCountPair(
    val sneaker: SneakerRemote,
    val quantity: Int
)
