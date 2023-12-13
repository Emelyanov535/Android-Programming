package com.example.android_programming.composeui.Screens.OrderScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun CardSneakerLike(item: Sneaker, basketViewModel: BasketViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val userId = GlobalUser.getInstance().getUser()?.userId!!
    val quantityState by basketViewModel.getQuantityState(userId, item.sneakerId!!).collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.figma)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = item.photo),
            contentDescription = "image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(70.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            item.brand?.let { Text(text = it, fontSize = 20.sp) }
            Text(text = "${item.price} USD", color = Color.Red, fontSize = 16.sp)
        }

        Column {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.figma_blue),
                    contentColor = Color.White
                ),
                onClick = {
                    basketViewModel.deleteSneakerFromBasket(1, item.sneakerId!!)
//                    runBlocking {
//                        launch(Dispatchers.Default) {
//                            basketViewModel.deleteSneakerFromBasket(basketViewModel.getUserBasketId(GlobalUser.getInstance().getUser()?.userId!!), item.sneakerId!!)
//                        }
//                    }
                },
                modifier = Modifier
                    .padding(end = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
            }
        }

        Column {
            Row {
                IconButton(onClick = { basketViewModel.decrementQuantity(userId ,item.sneakerId!!) }) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Decrease Quantity")
                }
                Text("$quantityState")
                IconButton(onClick = { basketViewModel.incrementQuantity(userId, item.sneakerId!!) }) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Increase Quantity")
                }
            }
        }
    }
}