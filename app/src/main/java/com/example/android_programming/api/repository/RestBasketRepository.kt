package com.example.android_programming.api.repository

import com.example.android_programming.api.BackendService
import com.example.android_programming.api.model.SneakerRemote
import com.example.android_programming.api.model.toBasketSneaker
import com.example.android_programming.api.model.toBasketSneakerRemote
import com.example.android_programming.api.model.toSneaker
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.model.Basket
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RestBasketRepository(
    private var service: BackendService
): BasketRepository {
    override suspend fun insertBasketSneaker(basketSneaker: BasketSneakers) {
        service.createBasketSneaker(basketSneaker.toBasketSneakerRemote())
    }

    override suspend fun getBasketWithSneakers(id: Int): Flow<List<Sneaker>> {
        val sneakersRemoteList = service.getUserBasketSneakers(id)
        val sneakersList = sneakersRemoteList.map { it.toSneaker() }
        return flowOf(sneakersList.toList())
    }

    override suspend fun getUserBasketId(id: Int): Int {
        return service.getUserBasket(id)
    }

    override suspend fun getQuantity(basketId: Int, sneakerId: Int): Int? {
        return service.getQuantity(basketId, sneakerId)
    }
//
//    override fun getAllBasket(): Flow<List<Basket>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun delete(basket: Basket) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun createBasket(basket: Basket): Long {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun removeSneakerFromBasket(basketId: Int, sneakerId: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun updateSneakerQuantity(basketId: Int, sneakerId: Int, quantity: Int) {
//        TODO("Not yet implemented")
//    }
//
    override suspend fun incrementSneakerQuantity(basketId: Int, sneakerId: Int) {
        service.increment(basketId, sneakerId)
    }

    override suspend fun decrementSneakerQuantity(basketId: Int, sneakerId: Int) {
        service.decrement(basketId, sneakerId)
    }
//
//    override suspend fun getQuantity(basketId: Int, sneakerId: Int): Int? {
//        TODO("Not yet implemented")
//    }
//
    override suspend fun existSneaker(basketId: Int, sneakerId: Int): Boolean {
        return service.getSneaker(basketId, sneakerId)
    }
//
//    override suspend fun getTotalPriceForUser(userId: Int): Double? {
//        TODO("Not yet implemented")
//    }
}