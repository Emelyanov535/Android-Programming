package com.example.android_programming.Screens.OrderScreen

import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_programming.R
import com.example.android_programming.Screens.LikeScreen.CardSneakerLike
import com.example.android_programming.SneakerItem


@Composable
fun OrderScreen() {

    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.figma)),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "Delivery Address", fontSize = 24.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = address,
                    onValueChange = { address = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {

                        }
                    ),
                    placeholder = {
                        Text(
                            text = "Address",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = city,
                    onValueChange = { city = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {

                        }
                    ),
                    placeholder = {
                        Text(
                            text = "City",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = number,
                    onValueChange = { number = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {

                        }
                    ),
                    placeholder = {
                        Text(
                            text = "Number",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }
                )
            }
        }
        Row {
            LazyColumn {
                itemsIndexed(
                    listOf(
                        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    )
                ){_, item->
                    CardSneakerLike(item = item)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.figma)),
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
                Text(text = "319.99 $", fontSize = 15.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.figma)),
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
                Text(text = "180 $", fontSize = 15.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.figma)),
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
                Text(text = "1900 $", fontSize = 15.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
        ) {
            Text("Confirm order")
        }
    }
}

@Composable
@Preview
fun OrderScreenPreview(){
    OrderScreen()
}