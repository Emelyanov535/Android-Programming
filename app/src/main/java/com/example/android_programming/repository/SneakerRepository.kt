package com.example.android_programming.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface SneakerRepository {
    suspend fun insertSneaker(sneaker: Sneaker)
    suspend fun updateSneaker(sneaker: Sneaker)
    suspend fun deleteSneaker(sneaker: Sneaker)
    suspend fun getSneakerById(id: Int): Sneaker
    fun getAllSneakers(): Flow<PagingData<Sneaker>>
}