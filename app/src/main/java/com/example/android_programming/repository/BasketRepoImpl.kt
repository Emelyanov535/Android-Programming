package com.example.android_programming.repository

import com.example.android_programming.dao.BasketDao
import com.example.android_programming.model.Basket
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import kotlinx.coroutines.flow.Flow

class BasketRepoImpl(private val basketDao: BasketDao): BasketRepository {
    override suspend fun createBasket(basket: Basket): Long = basketDao.createBasket(basket)
    override suspend fun removeSneakerFromBasket(basketId: Int, sneakerId: Int) = basketDao.removeSneakerFromBasket(basketId, sneakerId)
    override suspend fun updateSneakerQuantity(basketId: Int, sneakerId: Int, quantity: Int) = basketDao.updateSneakerQuantity(basketId, sneakerId, quantity)
    override suspend fun incrementSneakerQuantity(basketId: Int, sneakerId: Int) = basketDao.incrementSneakerQuantity(basketId, sneakerId)
    override suspend fun decrementSneakerQuantity(basketId: Int, sneakerId: Int) = basketDao.decrementSneakerQuantity(basketId, sneakerId)
    override suspend fun insertBasketSneaker(basketSneaker: BasketSneakers) = basketDao.insertBasketSneaker(basketSneaker)
    override fun getBasketWithSneakers(id: Int): Flow<BasketWithSneakers> = basketDao.getBasketWithSneakers(id)
    override fun getAllBasket(): Flow<List<Basket>> = basketDao.getAllBasket()
    override suspend fun delete(basket: Basket) = basketDao.delete(basket)
    override suspend fun getQuantity(basketId: Int, sneakerId: Int): Int? = basketDao.getQuantity(basketId, sneakerId)
    override suspend fun getSneaker(basketId: Int, sneakerId: Int): BasketSneakers? = basketDao.getSneaker(basketId, sneakerId)
    override suspend fun getTotalPriceForUser(userId: Int): Double? = basketDao.getTotalPriceForUser(userId)
}