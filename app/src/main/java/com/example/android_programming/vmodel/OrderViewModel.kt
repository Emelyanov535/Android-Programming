package com.example.android_programming.vmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.android_programming.App
import com.example.android_programming.GlobalUser
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.OrderWithSneakers
import com.example.android_programming.model.Sneaker
import com.example.android_programming.model.UserWithOrder
import com.example.android_programming.repository.OrderRepository
import com.example.android_programming.repository.SneakerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    private var _selectedItems = mutableStateOf<List<Sneaker>>(emptyList())
    val selectedItems get() = _selectedItems.value
    var city = mutableStateOf("")
    val street = mutableStateOf("")
    val house = mutableStateOf("")

    fun addSelectedItem(item: Sneaker) {
        _selectedItems.value = _selectedItems.value + item
    }

    fun deleteOrder(order: Order) = viewModelScope.launch {
        orderRepository.delete(order)
    }

    fun getOrderList(id: Int) : Flow<UserWithOrder> {
        return orderRepository.getUserOrders(id)
    }

    fun removeSelectedItem(item: Sneaker) {
        val updatedItems = _selectedItems.value.toMutableList()
        updatedItems.remove(item)
        _selectedItems.value = updatedItems
    }

    fun getOrderWithSneakers(id: Int) : Flow<OrderWithSneakers> {
        return orderRepository.getOrderWithSneakers(id)
    }

    fun createOrder() = viewModelScope.launch {
        val order = Order(
            date = Date().time,
            city = city.value,
            street = street.value,
            house = house.value,
            subtotal = getSubTotal(),
            taxes = "%.2f".format(getSubTotal() * 0.05).toDouble(),
            total = "%.2f".format(getSubTotal() * 0.05 + getSubTotal()).toDouble(),
            creatorUserId = GlobalUser.getInstance().getUser()?.userId!!
        )

        val orderId = orderRepository.createOrder(order)


        for (sneaker in selectedItems) {
            val orderSneaker = OrderSneaker( orderId.toInt(), sneaker.sneakerId!!)
            orderRepository.insertOrderSneaker(orderSneaker)
        }
        city.value = ""
        street.value = ""
        house.value = ""
        _selectedItems = mutableStateOf(emptyList())
    }

    fun getSubTotal(): Double {
        return selectedItems.sumOf { it.price }
    }
}