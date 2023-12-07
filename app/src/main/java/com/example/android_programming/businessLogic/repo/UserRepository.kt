package com.example.android_programming.businessLogic.repo

import com.example.android_programming.api.model.UserRemoteSignIn
import com.example.android_programming.model.Basket
import com.example.android_programming.model.User
import com.example.android_programming.model.UserWithOrder
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun authUser(user: UserRemoteSignIn): User
}