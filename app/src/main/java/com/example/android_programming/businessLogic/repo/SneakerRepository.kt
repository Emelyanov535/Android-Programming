package com.example.android_programming.businessLogic.repo

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface SneakerRepository {
    suspend fun insertSneaker(sneaker: Sneaker)
    suspend fun updateSneaker(sneaker: Sneaker)
    suspend fun deleteSneaker(sneaker: Sneaker)
    suspend fun getSneakerById(id: Int): Sneaker
    suspend fun getAllSneakers(): Flow<PagingData<Sneaker>>
    fun call(str: String): Flow<PagingData<Sneaker>>
    fun callAdidas(): Flow<PagingData<Sneaker>>
    fun callNike(): Flow<PagingData<Sneaker>>
}