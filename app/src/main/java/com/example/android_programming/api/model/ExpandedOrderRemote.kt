package com.example.android_programming.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ExpandedOrderRemote (
    val id: Int? = 0,
    val date: Long = 0L,
    val city: String = "",
    val street: String = "",
    val house: String = "",
    val subtotal: Double = 0.0,
    val taxes: Double = 0.0,
    val total: Double = 0.0,
    val user: String = "",
    val sneakerList: List<SneakerRemote>
)