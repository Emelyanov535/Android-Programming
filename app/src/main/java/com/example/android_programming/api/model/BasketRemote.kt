package com.example.android_programming.api.model

import kotlinx.serialization.Serializable

@Serializable
data class BasketRemote (
    val id: Int? = 0,
    val userId: Int = 0,
    )