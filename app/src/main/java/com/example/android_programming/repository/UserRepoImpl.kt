package com.example.android_programming.repository

import com.example.android_programming.dao.UserDao
import com.example.android_programming.model.User
import com.example.android_programming.model.UserWithOrder
import kotlinx.coroutines.flow.Flow

class UserRepoImpl(private val userDao: UserDao) : UserRepository {

    override suspend fun createUser(user: User) = userDao.createUser(user)

    override suspend fun updateUser(user: User) = userDao.updateUser(user)

    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    override suspend fun getUserById(id: Int): User = userDao.getUserById(id)

    override suspend fun getUserByEmail(email: String): User = userDao.getUserByEmail(email)

    override fun getUserOrders(id: Int): Flow<UserWithOrder> = userDao.getUserOrders(id)
}