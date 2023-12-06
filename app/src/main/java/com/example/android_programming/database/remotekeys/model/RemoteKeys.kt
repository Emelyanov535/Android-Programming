package com.example.android_programming.database.remotekeys.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.android_programming.model.Sneaker

enum class RemoteKeyType(private val type: String) {
    SNEAKER(Sneaker::class.simpleName ?: "Sneaker");
    @TypeConverter
    fun toRemoteKeyType(value: String) = RemoteKeyType.values().first { it.type == value }
    @TypeConverter
    fun fromRemoteKeyType(value: RemoteKeyType) = value.type
}
@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val entityId: Int,
    @TypeConverters(RemoteKeyType::class)
    val type: RemoteKeyType,
    val prevKey: Int?,
    val nextKey: Int?
)