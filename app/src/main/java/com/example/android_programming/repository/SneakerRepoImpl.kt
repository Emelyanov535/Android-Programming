package com.example.android_programming.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.android_programming.dao.SneakerDao
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

class SneakerRepoImpl(private val sneakerDao: SneakerDao) : SneakerRepository {

    override suspend fun insertSneaker(sneaker: Sneaker) = sneakerDao.insert(sneaker)

    override suspend fun updateSneaker(sneaker: Sneaker) = sneakerDao.update(sneaker)

    override suspend fun deleteSneaker(sneaker: Sneaker) = sneakerDao.delete(sneaker)

    override suspend fun getSneakerById(id: Int): Sneaker = sneakerDao.getSneakerById(id)
    override fun getAllSneakersPaged(): PagingSource<Int, Sneaker> = sneakerDao.getAllSneakersPaged()
    override fun call(): Flow<PagingData<Sneaker>> {
        return Pager(
            PagingConfig(pageSize = 5)
        ) {
            sneakerDao.getAllSneakersPaged()
        }.flow
    }
}