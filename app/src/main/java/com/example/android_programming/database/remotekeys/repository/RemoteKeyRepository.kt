package com.example.android_programming.database.remotekeys.repository

import com.example.android_programming.database.remotekeys.model.RemoteKeyType
import com.example.android_programming.database.remotekeys.model.RemoteKeys

interface RemoteKeyRepository {
    suspend fun getAllRemoteKeys(id: Int, type: RemoteKeyType): RemoteKeys?
    suspend fun createRemoteKeys(remoteKeys: List<RemoteKeys>)
    suspend fun deleteRemoteKey(type: RemoteKeyType)
}