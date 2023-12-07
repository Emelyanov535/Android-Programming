package com.example.android_programming.businessLogic.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import com.example.android_programming.businessLogic.repo.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BasketViewModel(private val basketRepository: BasketRepository): ViewModel() {

    private val _quantityStateMap = mutableMapOf<Int, MutableStateFlow<Int>>()

    fun getQuantityState(basketId: Int, sneakerId: Int): StateFlow<Int> {
        val quantityStateFlow = _quantityStateMap.getOrPut(sneakerId) {
            MutableStateFlow(0)
        }

        viewModelScope.launch {
            val quantityFromDb = basketRepository.getQuantity(basketId, sneakerId)
            quantityFromDb?.let { quantityStateFlow.value = it }
        }

        return quantityStateFlow
    }

    suspend fun isSneakerInBasket(basketId: Int, sneakerId: Int): Boolean {
        return basketRepository.getSneaker(basketId, sneakerId) != null
    }
    fun addToBasket(basketSneakers: BasketSneakers) = viewModelScope.launch {
        val isSneakerInBasket = isSneakerInBasket(basketSneakers.basketId, basketSneakers.sneakerId)

        if (isSneakerInBasket) {
            incrementQuantity(basketSneakers.basketId, basketSneakers.sneakerId)
        } else {
            basketRepository.insertBasketSneaker(basketSneakers)
        }
    }

    fun getBasketSneakers(id: Int): Flow<BasketWithSneakers> {
        return basketRepository.getBasketWithSneakers(id)
    }

    fun deleteSneakerFromBasket(basketId: Int, sneakerId: Int) = viewModelScope.launch {
        basketRepository.removeSneakerFromBasket(basketId, sneakerId)
    }

    fun incrementQuantity(basketId: Int, sneakerId: Int) {
        val currentQuantity = _quantityStateMap[sneakerId]?.value ?: 1
        _quantityStateMap[sneakerId]?.value = currentQuantity + 1

        viewModelScope.launch {
            basketRepository.incrementSneakerQuantity(basketId, sneakerId)
        }
    }

    fun decrementQuantity(basketId: Int, sneakerId: Int) {
        val currentQuantity = _quantityStateMap[sneakerId]?.value ?: 1
        if (currentQuantity > 1) {
            _quantityStateMap[sneakerId]?.value = currentQuantity - 1

            viewModelScope.launch {
                basketRepository.decrementSneakerQuantity(basketId, sneakerId)
            }
        }
    }
}
