package com.example.android_programming.api.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRemoteSignIn(
    val email: String = "",
    val password: String = "",
)