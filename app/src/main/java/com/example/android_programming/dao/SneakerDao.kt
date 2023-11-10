package com.example.android_programming.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerDao {
    @Insert
    suspend fun insert(sneaker: Sneaker)

    @Update
    suspend fun update(sneaker: Sneaker)

    @Delete
    suspend fun delete(sneaker: Sneaker)

    @Query("SELECT*FROM Sneaker")
    fun getAllSneakersPaged(): PagingSource<Int, Sneaker>

    @Query("SELECT * FROM Sneaker WHERE sneakerId = :id")
    suspend fun getSneakerById(id: Int): Sneaker
}