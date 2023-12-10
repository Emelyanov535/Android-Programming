package com.example.android_programming.businessLogic.repo

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_programming.model.Basket
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun insertBasketSneaker(basketSneaker: BasketSneakers)
    suspend fun getBasketWithSneakers(id: Int): Flow<List<Sneaker>>
    suspend fun getUserBasketId(id: Int): Int
//    fun getAllBasket(): Flow<List<Basket>>
//    suspend fun delete(basket: Basket)
//    suspend fun createBasket(basket: Basket):Long
    suspend fun removeSneakerFromBasket(basketId: Int, sneakerId: Int)
//    suspend fun updateSneakerQuantity(basketId: Int, sneakerId: Int, quantity: Int)
    suspend fun incrementSneakerQuantity(basketId: Int, sneakerId: Int)
    suspend fun decrementSneakerQuantity(basketId: Int, sneakerId: Int)
    suspend fun getQuantity(basketId: Int, sneakerId: Int): Int?
    suspend fun existSneaker(basketId: Int, sneakerId: Int): Boolean
    suspend fun getTotalPriceForUser(userId: Int): Double?
    suspend fun deleteAllSneakerFromBasket(basketId: Int)
}