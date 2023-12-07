package com.example.android_programming.database.repository

import com.example.android_programming.businessLogic.repo.RemoteKeyRepository
import com.example.android_programming.database.dao.RemoteKeysDao
import com.example.android_programming.model.RemoteKeyType
import com.example.android_programming.model.RemoteKeys

class RemoteKeysRepositoryImpl(private val remoteKeysDao: RemoteKeysDao) : RemoteKeyRepository {
    override suspend fun getAllRemoteKeys(id: Int, type: RemoteKeyType) =
    remoteKeysDao.getRemoteKeys(id, type)

    override suspend fun createRemoteKeys(remoteKeys: List<RemoteKeys>) =
        remoteKeysDao.insertAll(remoteKeys)

    override suspend fun deleteRemoteKey(type: RemoteKeyType) =
        remoteKeysDao.clearRemoteKeys(type)
}