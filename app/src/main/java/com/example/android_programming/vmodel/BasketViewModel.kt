package com.example.android_programming.vmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.model.Basket
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import com.example.android_programming.model.Sneaker
import com.example.android_programming.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BasketViewModel(private val basketRepository: BasketRepository): ViewModel() {

    fun addToBasket(basketSneakers: BasketSneakers) = viewModelScope.launch {
        basketRepository.insertBasketSneaker(basketSneakers)
    }

    fun getBasketSneakers(id: Int): Flow<BasketWithSneakers> {
        return basketRepository.getBasketWithSneakers(id)
    }

    fun deleteSneakerFromBasket(basketId: Int, sneakerId: Int) = viewModelScope.launch {
        basketRepository.removeSneakerFromBasket(basketId, sneakerId)
    }
}