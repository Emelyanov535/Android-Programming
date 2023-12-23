package com.example.android_programming.database.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.android_programming.di.AppContainer
import com.example.android_programming.businessLogic.repo.SneakerRepository
import com.example.android_programming.database.dao.SneakerDao
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

class SneakerRepoImpl(private val sneakerDao: SneakerDao) : SneakerRepository {

    override suspend fun insertSneaker(sneaker: Sneaker) = sneakerDao.insert(sneaker)

    override suspend fun updateSneaker(sneaker: Sneaker) = sneakerDao.update(sneaker)

    override suspend fun deleteSneaker(sneaker: Sneaker) = sneakerDao.delete(sneaker)

    override suspend fun getSneakerById(id: Int): Sneaker = sneakerDao.getSneakerById(id)
    override fun getAllSneakers(): Flow<PagingData<Sneaker>> = Pager(
        config = PagingConfig(
            pageSize = AppContainer.LIMIT,
            enablePlaceholders = false
        ),
        pagingSourceFactory = sneakerDao::getAll
    ).flow

    suspend fun clearSneakers() = sneakerDao.deleteAll()
    suspend fun insertSneakers(sneakers: List<Sneaker>) =
        sneakerDao.insert(*sneakers.toTypedArray())

    fun getAllSneakersPagingSource(): PagingSource<Int, Sneaker> = sneakerDao.getAll()

    override fun call(str: String): Flow<PagingData<Sneaker>> {
        return Pager(
            PagingConfig(
                pageSize = AppContainer.LIMIT,
                enablePlaceholders = false
            ),
        ) {
            sneakerDao.findSneakersByBrandOrModel(str)
        }.flow
    }

    override fun callAdidas(): Flow<PagingData<Sneaker>> {
        return Pager(
            PagingConfig(
                pageSize = AppContainer.LIMIT,
                enablePlaceholders = false
            ),
        ) {
            sneakerDao.findSneakersByAdidas()
        }.flow
    }

    override fun callNike(): Flow<PagingData<Sneaker>> {
        return Pager(
            PagingConfig(
                pageSize = AppContainer.LIMIT,
                enablePlaceholders = false
            ),
        ) {
            sneakerDao.findSneakersByNike()
        }.flow
    }
}