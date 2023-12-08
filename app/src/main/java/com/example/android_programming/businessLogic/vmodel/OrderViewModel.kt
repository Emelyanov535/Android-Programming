package com.example.android_programming.businessLogic.vmodel

import androidx.compose.runtime.State
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class OrderViewModel(private val orderRepository: OrderRepository, private val basketRepository: BasketRepository) : ViewModel() {

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

    fun deleteOrder(order: Order) = viewModelScope.launch {
        orderRepository.delete(order)
    }

    fun getOrderList(id: Int) : Flow<UserWithOrder> {
        return orderRepository.getUserOrders(id)
    }

    fun getOrderWithSneakers(id: Int) : Flow<OrderWithSneakers> {
        return orderRepository.getOrderWithSneakers(id)
    }

//    fun createOrder() = viewModelScope.launch {
//        val userId = GlobalUser.getInstance().getUser()?.userId!!
//        val order = Order(
//            date = Date().time,
//            city = city.value,
//            street = street.value,
//            house = house.value,
//            subtotal = getSubTotal(userId),
//            taxes = "%.2f".format(getSubTotal(userId) * 0.05).toDouble(),
//            total = "%.2f".format(getSubTotal(userId) * 0.05 + getSubTotal(userId)).toDouble(),
//            creatorUserId = GlobalUser.getInstance().getUser()?.userId!!
//        )
//
//        val orderId = orderRepository.createOrder(order)
//
////        for (sneaker in selectedItems.value.orEmpty()) {
////            val userId = GlobalUser.getInstance().getUser()?.userId!!
////            val orderSneaker = basketRepository.getQuantity(userId, sneaker.sneakerId!!)
////                ?.let { OrderSneaker( orderId.toInt(), sneaker.sneakerId!!, it) }
////            if (orderSneaker != null) {
////                orderRepository.insertOrderSneaker(orderSneaker)
////            }
////        }
//        city.value = ""
//        street.value = ""
//        house.value = ""
//    }

//    fun updateSubTotal(userId: Int) {
//        viewModelScope.launch {
//            _subTotal.value = getSubTotal(userId)
//        }
//    }

//    suspend fun getSubTotal(userId: Int): Double {
//        return basketRepository.getTotalPriceForUser(userId) ?: 0.0
//    }
}