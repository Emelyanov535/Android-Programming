package com.example.android_programming.api.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.android_programming.model.RoleEnum
import com.example.android_programming.model.Sneaker
import com.example.android_programming.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserRemote (
    val id: Int? = 0,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val role: RoleEnum = RoleEnum.User,
    val photo: Int? = 0,
)

fun UserRemote.toUser(): User = User(
    id,
    name,
    surname,
    email,
    password,
    role,
    photo
)

fun User.toUserRemote():UserRemote = UserRemote(
    userId,
    name,
    surname,
    email,
    password,
    role,
    photo
)