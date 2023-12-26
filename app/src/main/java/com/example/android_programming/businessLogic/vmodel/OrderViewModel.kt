package com.example.android_programming.businessLogic.vmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.GlobalUser
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.OrderWithSneakers
import com.example.android_programming.model.Sneaker
import com.example.android_programming.model.UserWithOrder
import com.example.android_programming.businessLogic.repo.BasketRepository
import com.example.android_programming.businessLogic.repo.OrderRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class OrderViewModel(private val orderRepository: OrderRepository, private val basketRepository: BasketRepository) : MyViewModel() {

    var city = mutableStateOf("")
    val street = mutableStateOf("")
    val house = mutableStateOf("")
    private val _selectedItems = MutableLiveData<List<Sneaker>>()
    private val _subTotal = mutableStateOf(0.0)
    val subTotal: State<Double> get() = _subTotal
    val selectedItems: LiveData<List<Sneaker>> get() = _selectedItems

    fun updateSelectedItems(items: List<Sneaker>) {
        _selectedItems.value = items
    }

    fun deleteOrder(orderId: Int) = viewModelScope.launch {
        orderRepository.delete(orderId)
    }

    suspend fun getOrderList(id: Int) : Flow<List<Order>> {
        return orderRepository.getUserOrders(id)
    }

    suspend fun getOrderWithSneakers(id: Int) : Flow<List<Sneaker>> {
        return orderRepository.getSneakerFromOrder(id)
    }

    fun createOrder() = viewModelScope.launch {
        val userId = GlobalUser.getInstance().getUser()?.userId!!
        Log.d("USER ID", userId.toString())
        val userBasketId = basketRepository.getUserBasketId(userId).first()
        Log.d("USER BASKET ID", userBasketId.toString())
        val subTotal = getSubTotal(userId)
        val order = Order(
            date = Date().time,
            city = city.value,
            street = street.value,
            house = house.value,
            subtotal = subTotal,
            taxes = "%.2f".format(subTotal * 0.05).toDouble(),
            total = "%.2f".format(subTotal * 0.05 + subTotal).toDouble(),
            creatorUserId = GlobalUser.getInstance().getUser()?.userId!!
        )
        val orderId = orderRepository.createOrder(order)
        for (sneaker in selectedItems.value.orEmpty()) {
            val quantity = basketRepository.getQuantity(userBasketId, sneaker.sneakerId!!).first()
            Log.d("QUANTITY", quantity.toString())
            val orderSneaker =  OrderSneaker( orderId.toInt(), sneaker.sneakerId, quantity)
            orderRepository.insertOrderSneaker(orderSneaker)
        }
        city.value = ""
        street.value = ""
        house.value = ""
        basketRepository.deleteAllSneakerFromBasket(userBasketId)
    }


    fun updateSubTotal(userId: Int) {
        viewModelScope.launch {
            _subTotal.value = getSubTotal(userId)
        }
    }

    suspend fun getSubTotal(userId: Int): Double {
        return basketRepository.getTotalPriceForUser(userId) ?: 0.0
    }
}