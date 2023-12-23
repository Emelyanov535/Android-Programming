package com.example.android_programming.api.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.android_programming.di.AppContainer
import com.example.android_programming.api.BackendService
import com.example.android_programming.api.SneakerRemoteMediator
import com.example.android_programming.api.model.toSneaker
import com.example.android_programming.api.model.toSneakerRemote
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.database.repository.RemoteKeysRepositoryImpl
import com.example.android_programming.model.Sneaker
import com.example.android_programming.database.repository.SneakerRepoImpl
import com.example.android_programming.businessLogic.repo.SneakerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class RestSneakerRepository(
    private val service: BackendService,
    private val dbSneakerRepository: SneakerRepoImpl,
    private val database: AppDatabase,
    private val dbRemoteKeyRepository: RemoteKeysRepositoryImpl

) : SneakerRepository {
    override fun getAllSneakers(): Flow<PagingData<Sneaker>> {
        val pagingSourceFactory = { dbSneakerRepository.getAllSneakersPagingSource() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = AppContainer.LIMIT,
                enablePlaceholders = false
            ),
            remoteMediator = SneakerRemoteMediator(
                service,
                dbSneakerRepository,
                database,
                dbRemoteKeyRepository,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun call(str: String): Flow<PagingData<Sneaker>> {
        return dbSneakerRepository.call(str)
    }

    override fun callAdidas(): Flow<PagingData<Sneaker>> {
        return dbSneakerRepository.callAdidas()
    }

    override fun callNike(): Flow<PagingData<Sneaker>> {
        return dbSneakerRepository.callNike()
    }

    override suspend fun getSneakerById(id: Int): Sneaker = service.getSneaker(id).toSneaker()
    override suspend fun insertSneaker(sneaker: Sneaker) {
        service.createSneaker(sneaker.toSneakerRemote())
    }

    override suspend fun updateSneaker(sneaker: Sneaker) {
        sneaker.sneakerId?.let { service.updateSneaker(it, sneaker.toSneakerRemote()) }
    }

    override suspend fun deleteSneaker(sneaker: Sneaker) {
        sneaker.sneakerId?.let { service.deleteSneaker(it) }
    }
}