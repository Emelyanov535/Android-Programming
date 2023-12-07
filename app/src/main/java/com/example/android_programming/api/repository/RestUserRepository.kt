package com.example.android_programming.api.repository

import com.example.android_programming.api.BackendService
import com.example.android_programming.api.model.UserRemoteSignIn
import com.example.android_programming.api.model.toUser
import com.example.android_programming.api.model.toUserRemote
import com.example.android_programming.model.User
import com.example.android_programming.businessLogic.repo.UserRepository

class RestUserRepository(
    private var service: BackendService
): UserRepository {
    override suspend fun createUser(user: User) {
        service.SignUp(user.toUserRemote())
    }

    override suspend fun updateUser(user: User) {
        println()
    }

    override suspend fun deleteUser(user: User) {
        println()
    }
    override suspend fun authUser(user: UserRemoteSignIn): User {
        return service.SignIn(user).toUser()
    }
}