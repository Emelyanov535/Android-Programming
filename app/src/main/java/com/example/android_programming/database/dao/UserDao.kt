package com.example.android_programming.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android_programming.model.User
import com.example.android_programming.model.UserWithOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun createUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE userId = :id")
    suspend fun getUserById(id: Int): User

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User
}