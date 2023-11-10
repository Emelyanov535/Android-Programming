package com.example.android_programming.repository

import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface SneakerRepository {
    suspend fun insertSneaker(sneaker: Sneaker)
    suspend fun updateSneaker(sneaker: Sneaker)
    suspend fun deleteSneaker(sneaker: Sneaker)
    suspend fun getSneakerById(id: Int): Sneaker
    fun getAllSneakers(): Flow<List<Sneaker>>
}