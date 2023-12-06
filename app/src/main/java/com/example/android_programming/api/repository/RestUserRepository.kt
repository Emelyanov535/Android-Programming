package com.example.android_programming.api.repository

import com.example.android_programming.api.BackendService
import com.example.android_programming.api.model.toUserRemote
import com.example.android_programming.model.User
import com.example.android_programming.repository.UserRepository

class RestUserRepository(
    private var service: BackendService
): UserRepository {
    override suspend fun createUser(user: User) {
        val user1 = user.toUserRemote()
        service.SignUp(user1)
    }

    override suspend fun updateUser(user: User) {
        println()
    }

    override suspend fun deleteUser(user: User) {
        println()
    }

}