package com.example.android_programming.repository

import com.example.android_programming.model.Basket
import com.example.android_programming.model.User
import com.example.android_programming.model.UserWithOrder
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getUserById(id: Int): User
    suspend fun getUserByEmail(email: String): User
}