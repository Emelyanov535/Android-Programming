package com.example.android_programming.businessLogic.vmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.GlobalUser
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.BasketWithSneakers
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BasketViewModel(private val basketRepository: BasketRepository): ViewModel() {

    private val _quantityStateMap = mutableMapOf<Int, MutableStateFlow<Int>>()

    private val _sneakerList = MutableStateFlow<List<Sneaker>>(emptyList())
    val sneakerList: StateFlow<List<Sneaker>> = _sneakerList

    suspend fun getQuantityState(basketId: Int, sneakerId: Int): StateFlow<Int> {
        val quantityStateFlow = _quantityStateMap.getOrPut(sneakerId) {
            MutableStateFlow(0)
        }

        viewModelScope.launch {
            val quantityFromDb = basketRepository.getQuantity(basketId, sneakerId).first()
            quantityFromDb?.let { quantityStateFlow.value = it }
        }

        return quantityStateFlow
    }

    suspend fun isSneakerInBasket(basketId: Int, sneakerId: Int): Boolean {
        return basketRepository.existSneaker(basketId, sneakerId)
    }
    suspend fun addToBasket(basketSneakers: BasketSneakers) = viewModelScope.launch {
        val isSneakerInBasket = isSneakerInBasket(basketSneakers.basketId, basketSneakers.sneakerId)

        if (isSneakerInBasket) {
            incrementQuantity(basketSneakers.basketId, basketSneakers.sneakerId)
        } else {
            basketRepository.insertBasketSneaker(basketSneakers)
        }
    }
    suspend fun fetchBasketSneakers(userId: Int) {
        viewModelScope.launch {
            basketRepository.getBasketWithSneakers(userId).collect {
                _sneakerList.value = it
            }
        }
    }

    suspend fun getUserBasketId(userId: Int) : Flow<Int>{
        return basketRepository.getUserBasketId(userId)
    }


    suspend fun deleteSneakerFromBasket(basketId: Int, sneakerId: Int) = viewModelScope.launch {
        basketRepository.removeSneakerFromBasket(basketId, sneakerId)
        fetchBasketSneakers(GlobalUser.getInstance().getUser()?.userId!!)
    }

    suspend fun incrementQuantity(basketId: Int, sneakerId: Int) {
        val currentQuantity = _quantityStateMap[sneakerId]?.value ?: 1
        _quantityStateMap[sneakerId]?.value = currentQuantity + 1

        viewModelScope.launch {
            basketRepository.incrementSneakerQuantity(basketId, sneakerId)
        }
    }

    suspend fun decrementQuantity(basketId: Int, sneakerId: Int) {
        val currentQuantity = _quantityStateMap[sneakerId]?.value ?: 1
        if (currentQuantity > 1) {
            _quantityStateMap[sneakerId]?.value = currentQuantity - 1

            viewModelScope.launch {
                basketRepository.decrementSneakerQuantity(basketId, sneakerId)
            }
        }
    }
    fun deleteAllSneakerFromBasket(basketId: Int) = viewModelScope.launch {
        basketRepository.deleteAllSneakerFromBasket(basketId)
    }
}
