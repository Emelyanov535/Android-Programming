package com.example.android_programming.dao

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
    suspend fun update(sneaker: Sneaker?)

    @Delete
    suspend fun delete(sneaker: Sneaker)

    @Query("SELECT*FROM sneakers")
    fun getAllSneakers(): Flow<List<Sneaker>>

    @Query("SELECT * FROM sneakers WHERE id = :id")
    suspend fun getSneakerById(id: Int): Sneaker
}