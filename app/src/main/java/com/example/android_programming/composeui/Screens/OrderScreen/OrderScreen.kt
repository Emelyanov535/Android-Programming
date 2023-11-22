package com.example.android_programming.composeui.Screens.OrderScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.GlobalUser
import com.example.android_programming.R
import com.example.android_programming.model.Sneaker
import com.example.android_programming.vmodel.AppViewModelProvider
import com.example.android_programming.vmodel.BasketViewModel
import com.example.android_programming.vmodel.OrderViewModel


@Composable
fun OrderScreen(navHostController: NavHostController, basketViewModel: BasketViewModel = viewModel(factory = AppViewModelProvider.Factory), orderViewModel: OrderViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 60.dp)
            .verticalScroll(rememberScrollState())
    ){
        DeliveryAddress(orderViewModel)
        val userId = GlobalUser.getInstance().getUser()?.userId
        if (userId != null) {
            val list by basketViewModel.getBasketSneakers(userId!!).collectAsState(initial = null)
            val SneakerList: List<Sneaker>? = list?.sneakers
            if (SneakerList != null) {
                orderViewModel.updateSelectedItems(SneakerList)
                ShoppingList(SneakerList)
                SubTotal(orderViewModel)
            }
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.figma_blue),
                contentColor = Color.White
            ),
            onClick = {
                if(GlobalUser.getInstance().getUser() != null){
                    orderViewModel.createOrder()
                    navHostController.navigate("home")
                }else{
                    navHostController.navigate("login")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 16.dp)
        ) {
            Text("Confirm order")
        }
    }
}

