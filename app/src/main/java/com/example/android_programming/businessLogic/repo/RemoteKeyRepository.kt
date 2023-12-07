package com.example.android_programming.businessLogic.repo

import com.example.android_programming.model.RemoteKeyType
import com.example.android_programming.model.RemoteKeys

interface RemoteKeyRepository {
    suspend fun getAllRemoteKeys(id: Int, type: RemoteKeyType): RemoteKeys?
    suspend fun createRemoteKeys(remoteKeys: List<RemoteKeys>)
    suspend fun deleteRemoteKey(type: RemoteKeyType)
}