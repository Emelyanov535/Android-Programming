package com.example.android_programming.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_programming.model.Basket
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Insert
    suspend fun createBasket(basket: Basket):Long

    @Insert
    suspend fun insertBasketSneaker(basketSneaker: BasketSneakers)

    @Query("SELECT * FROM 'Basket' WHERE creatorUserId = :id")
    fun getBasketWithSneakers(id: Int): Flow<BasketWithSneakers>

    @Query("SELECT * FROM 'Basket'")
    fun getAllBasket(): Flow<List<Basket>>

    @Delete
    suspend fun delete(basket: Basket)

    @Query("DELETE FROM 'BasketSneakers' WHERE basketId = :basketId AND sneakerId = :sneakerId")
    suspend fun removeSneakerFromBasket(basketId: Int, sneakerId: Int)

    @Query("UPDATE 'BasketSneakers' SET quantity = :quantity WHERE basketId = :basketId AND sneakerId = :sneakerId")
    suspend fun updateSneakerQuantity(basketId: Int, sneakerId: Int, quantity: Int)

    @Query("UPDATE 'BasketSneakers' SET quantity = quantity + 1 WHERE basketId = :basketId AND sneakerId = :sneakerId")
    suspend fun incrementSneakerQuantity(basketId: Int, sneakerId: Int)

    @Query("UPDATE 'BasketSneakers' SET quantity = quantity - 1 WHERE basketId = :basketId AND sneakerId = :sneakerId")
    suspend fun decrementSneakerQuantity(basketId: Int, sneakerId: Int)

    @Query("SELECT quantity FROM 'BasketSneakers' WHERE basketId = :basketId AND sneakerId = :sneakerId")
    suspend fun getQuantity(basketId: Int, sneakerId: Int): Int?
    @Query("SELECT * FROM 'BasketSneakers' WHERE basketId = :basketId AND sneakerId = :sneakerId")
    suspend fun getSneaker(basketId: Int, sneakerId: Int): BasketSneakers?
    @Query("SELECT SUM(price * quantity) FROM 'BasketSneakers' bs JOIN 'Sneaker' s ON bs.sneakerId = s.sneakerId WHERE bs.basketId = :userId")
    suspend fun getTotalPriceForUser(userId: Int): Double?
}