package com.example.android_programming.api

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.android_programming.api.model.toSneaker
import com.example.android_programming.api.repository.RestSneakerRepository
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.database.remotekeys.model.RemoteKeyType
import com.example.android_programming.database.remotekeys.model.RemoteKeys
import com.example.android_programming.database.remotekeys.repository.RemoteKeyRepository
import com.example.android_programming.database.remotekeys.repository.RemoteKeysRepositoryImpl
import com.example.android_programming.model.Sneaker
import com.example.android_programming.repository.SneakerRepoImpl
import com.example.android_programming.repository.SneakerRepository
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class SneakerRemoteMediator(
    private val service: BackendService,
    private val sneakerRepository: SneakerRepoImpl,
    private val database: AppDatabase,
    private val dbRemoteKeyRepository:RemoteKeysRepositoryImpl
) : RemoteMediator<Int, Sneaker>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Sneaker>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val sneakers = service.getSneakers(page, state.config.pageSize).map { it.toSneaker() }
            val endOfPaginationReached = sneakers.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dbRemoteKeyRepository.deleteRemoteKey(RemoteKeyType.SNEAKER)
                    sneakerRepository.clearSneakers()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = sneakers.map {
                    RemoteKeys(
                        entityId = it.sneakerId!!,
                        type = RemoteKeyType.SNEAKER,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                dbRemoteKeyRepository.createRemoteKeys(keys)
                sneakerRepository.insertSneakers(sneakers)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Sneaker>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { sneaker ->
                sneaker.sneakerId?.let { dbRemoteKeyRepository.getAllRemoteKeys(it, RemoteKeyType.SNEAKER) }
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Sneaker>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { sneaker ->
                sneaker.sneakerId?.let { dbRemoteKeyRepository.getAllRemoteKeys(it, RemoteKeyType.SNEAKER) }
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Sneaker>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.sneakerId?.let { sneakerUid ->
                dbRemoteKeyRepository.getAllRemoteKeys(sneakerUid, RemoteKeyType.SNEAKER)
            }
        }
    }
}