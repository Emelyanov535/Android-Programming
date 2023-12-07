package com.example.android_programming.businessLogic.repo

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_programming.model.Basket
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun insertBasketSneaker(basketSneaker: BasketSneakers)
    fun getBasketWithSneakers(id: Int): Flow<BasketWithSneakers>
    fun getAllBasket(): Flow<List<Basket>>
    suspend fun delete(basket: Basket)
    suspend fun createBasket(basket: Basket):Long
    suspend fun removeSneakerFromBasket(basketId: Int, sneakerId: Int)
    suspend fun updateSneakerQuantity(basketId: Int, sneakerId: Int, quantity: Int)
    suspend fun incrementSneakerQuantity(basketId: Int, sneakerId: Int)
    suspend fun decrementSneakerQuantity(basketId: Int, sneakerId: Int)
    suspend fun getQuantity(basketId: Int, sneakerId: Int): Int?
    suspend fun getSneaker(basketId: Int, sneakerId: Int): BasketSneakers?
    suspend fun getTotalPriceForUser(userId: Int): Double?
}