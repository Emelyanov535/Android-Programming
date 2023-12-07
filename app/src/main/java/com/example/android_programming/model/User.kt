package com.example.android_programming.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Surname")
    val surname: String,
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "Password")
    val password: String,
    @ColumnInfo(name = "Role")
    val role: String,
    @ColumnInfo(name = "Photo")
    val photo: Int? = null,
)
