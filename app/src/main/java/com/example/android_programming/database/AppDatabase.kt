package com.example.android_programming.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_programming.dao.SneakerDao
import com.example.android_programming.model.Sneaker

@Database(entities = [Sneaker::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sneakerDao(): SneakerDao

    companion object {
        private const val DB_NAME: String = "my-db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}