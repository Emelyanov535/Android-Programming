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
import androidx.compose.runtime.remember
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
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.BasketViewModel
import com.example.android_programming.businessLogic.vmodel.OrderViewModel
import com.example.android_programming.model.BasketSneakers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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
            basketViewModel.fetchBasketSneakers(userId!!)
            val sneakerList: List<Sneaker>? = basketViewModel.sneakerList.collectAsState(null).value
            if (sneakerList != null) {
                orderViewModel.updateSelectedItems(sneakerList)
                ShoppingList(sneakerList)
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
                    runBlocking {
                        launch(Dispatchers.Default) {
                            basketViewModel.deleteAllSneakerFromBasket(basketViewModel.getUserBasketId(userId!!))                        }
                    }
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

