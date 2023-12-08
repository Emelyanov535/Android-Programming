package com.example.android_programming.composeui.Screens.OrderScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_programming.GlobalUser
import com.example.android_programming.R
import com.example.android_programming.businessLogic.vmodel.OrderViewModel

@Composable
fun SubTotal(orderViewModel: OrderViewModel) {
    val userId = GlobalUser.getInstance().getUser()?.userId!!
//    orderViewModel.updateSubTotal(userId)
    val subTotal = orderViewModel.subTotal.value
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.figma))
    ){
        Row(
            modifier = Modifier
                .padding(10.dp, 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                Text(text = "Sub total", fontSize = 15.sp)
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ){
                Text(text = "$subTotal $", fontSize = 15.sp)
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp, 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                Text(text = "Taxes", fontSize = 15.sp)
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ){
                Text(text = "${"%.2f".format(subTotal * 0.05)} $", fontSize = 15.sp)
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp, 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ){
                Text(text = "Total", fontSize = 15.sp)
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ){
                Text(text = "${"%.2f".format(subTotal + subTotal * 0.05)} $", fontSize = 15.sp)
            }
        }
    }
}