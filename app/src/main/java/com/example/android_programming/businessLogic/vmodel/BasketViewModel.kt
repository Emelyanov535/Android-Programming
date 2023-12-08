package com.example.android_programming.businessLogic.vmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BasketViewModel(private val basketRepository: BasketRepository): ViewModel() {

    private val _quantityStateMap = mutableMapOf<Int, MutableStateFlow<Int>>()

    private val _sneakerList = MutableStateFlow<List<Sneaker>>(emptyList())
    val sneakerList: StateFlow<List<Sneaker>> = _sneakerList.asStateFlow()

    private val _basketId = MutableLiveData<Int>()
    val basketId: LiveData<Int> get() = _basketId

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
//
//    suspend fun isSneakerInBasket(basketId: Int, sneakerId: Int): Boolean {
//        return basketRepository.getSneaker(basketId, sneakerId) != null
//    }
    fun addToBasket(basketSneakers: BasketSneakers) = viewModelScope.launch {
    basketRepository.insertBasketSneaker(basketSneakers)
//        val isSneakerInBasket = isSneakerInBasket(basketSneakers.basketId, basketSneakers.sneakerId)
//
//        if (isSneakerInBasket) {
//            incrementQuantity(basketSneakers.basketId, basketSneakers.sneakerId)
//        } else {
//            basketRepository.insertBasketSneaker(basketSneakers)
//        }
    }
//
    fun fetchBasketSneakers(userId: Int) {
        viewModelScope.launch {
            basketRepository.getBasketWithSneakers(userId).collect {
                _sneakerList.emit(it)
            }
        }
    }

//    fun getUserBasketId(userId: Int) {
//        viewModelScope.async {
//            val basket = basketRepository.getUserOrder(userId)
//            _basketId.value = basket
//        }.await()
//    }

//
//    fun deleteSneakerFromBasket(basketId: Int, sneakerId: Int) = viewModelScope.launch {
//        basketRepository.removeSneakerFromBasket(basketId, sneakerId)
//    }
//
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
