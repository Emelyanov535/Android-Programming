package com.example.android_programming.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerDao {
    @Insert
    suspend fun insert(vararg sneaker: Sneaker)

    @Update
    suspend fun update(sneaker: Sneaker)

    @Delete
    suspend fun delete(sneaker: Sneaker)

    @Query("SELECT * FROM Sneaker WHERE LOWER(brand) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(model) LIKE '%' || LOWER(:searchString) || '%'")
    fun findSneakersByBrandOrModel(searchString: String): PagingSource<Int, Sneaker>
    @Query("select * from Sneaker")
    fun getAll(): PagingSource<Int, Sneaker>
    @Query("SELECT * FROM Sneaker WHERE sneakerId = :id")
    suspend fun getSneakerById(id: Int): Sneaker
    @Query("DELETE FROM Sneaker")
    suspend fun deleteAll()
    @Query("SELECT * FROM Sneaker WHERE brand='ADIDAS' or brand='adidas' or brand='Adidas'")
    fun findSneakersByAdidas(): PagingSource<Int, Sneaker>
    @Query("SELECT * FROM Sneaker WHERE brand='NIKE' or brand='nike' or brand='Nike'")
    fun findSneakersByNike(): PagingSource<Int, Sneaker>
}