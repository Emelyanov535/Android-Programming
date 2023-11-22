package com.example.android_programming.composeui.Screens.MyOrderScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_programming.GlobalUser
import com.example.android_programming.model.Order
import com.example.android_programming.vmodel.AppViewModelProvider
import com.example.android_programming.vmodel.OrderViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun MyOrderScreen(orderViewModel: OrderViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val userId = GlobalUser.getInstance().getUser()?.userId
    val userWithOrder = orderViewModel.getOrderList(userId!!).collectAsState(null).value?.orders

    Column(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())

    ){
        Text(
            text = "My order",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp, 10.dp)
        )
        Row {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (userWithOrder != null) {
                    for (item in userWithOrder) {
                        OrderCard(item)
                    }
                }
            }
        }
    }
}
