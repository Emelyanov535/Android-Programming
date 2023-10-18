package com.example.android_programming.vmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.android_programming.App
import com.example.android_programming.GlobalUser
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.launch
import java.util.Date

class OrderViewModel(val database: AppDatabase) : ViewModel() {
    private var _selectedItems = mutableStateOf<List<Sneaker>>(emptyList())
    val selectedItems get() = _selectedItems.value
    var city = mutableStateOf("")
    val street = mutableStateOf("")
    val house = mutableStateOf("")

    fun addSelectedItem(item: Sneaker) {
        _selectedItems.value = _selectedItems.value + item
    }

    fun deleteOrder(order: Order) = viewModelScope.launch {
        database.orderDao().delete(order)
    }

    fun getOrderList(id: Int) = viewModelScope.launch {
        database.userDao().getUserOrders(id)
    }

    fun removeSelectedItem(item: Sneaker) {
        val updatedItems = _selectedItems.value.toMutableList()
        updatedItems.remove(item)
        _selectedItems.value = updatedItems
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

        val orderId = database.orderDao().createOrder(order)

        for (sneaker in selectedItems) {
            val orderSneaker = OrderSneaker( orderId.toInt(), sneaker.sneakerId!!)
            database.orderDao().insertOrderSneaker(orderSneaker)
        }
        city.value = ""
        street.value = ""
        house.value = ""
        _selectedItems = mutableStateOf(emptyList())
    }

    fun getSubTotal(): Double {
        return selectedItems.sumOf { it.price }
    }

    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
                return OrderViewModel(database) as T
            }
        }
    }
}