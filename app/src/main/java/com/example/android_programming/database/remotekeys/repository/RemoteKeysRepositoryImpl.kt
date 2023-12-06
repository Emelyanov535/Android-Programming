package com.example.android_programming.database.remotekeys.repository

import com.example.android_programming.database.remotekeys.dao.RemoteKeysDao
import com.example.android_programming.database.remotekeys.model.RemoteKeyType
import com.example.android_programming.database.remotekeys.model.RemoteKeys

class RemoteKeysRepositoryImpl(private val remoteKeysDao: RemoteKeysDao) : RemoteKeyRepository {
    override suspend fun getAllRemoteKeys(id: Int, type: RemoteKeyType) =
    remoteKeysDao.getRemoteKeys(id, type)

    override suspend fun createRemoteKeys(remoteKeys: List<RemoteKeys>) =
        remoteKeysDao.insertAll(remoteKeys)

    override suspend fun deleteRemoteKey(type: RemoteKeyType) =
        remoteKeysDao.clearRemoteKeys(type)
}