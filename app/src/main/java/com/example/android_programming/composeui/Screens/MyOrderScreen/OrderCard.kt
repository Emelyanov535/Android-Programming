package com.example.android_programming.composeui.Screens.MyOrderScreen

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_programming.R
import com.example.android_programming.model.Order
import com.example.android_programming.model.Sneaker
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.OrderViewModel
import kotlinx.coroutines.flow.first
import java.util.Date

@Composable
fun OrderCard(order: Order, orderViewModel: OrderViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    var sneakers by remember { mutableStateOf<List<Sneaker>>(emptyList()) }
    LaunchedEffect(order.orderId) {
        sneakers = orderViewModel.getOrderWithSneakers(order.orderId!!).first()
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.figma))
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ){
            Text("№ ${order.orderId}")
            Text("${Date(order.date)}")
            Row(){
                if (sneakers != null) {
                    for(sneaker in sneakers){
                        Image(
                            contentScale = ContentScale.FillBounds,
                            bitmap = sneaker.photo.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp)
                                .padding(0.dp, 10.dp, 10.dp, 10.dp)
                        )
                    }
                }
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.figma_blue),
                    contentColor = Color.White
                ),
                onClick = {
                    orderViewModel.deleteOrder(order.orderId!!)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
            ) {
                Text("Cancel")
            }
        }
    }
}