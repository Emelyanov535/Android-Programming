package com.example.android_programming.Screens.AdminPanel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_programming.R
import com.example.android_programming.SneakerItem
import com.google.gson.Gson

@Composable
fun CardSneakerForChange(item: SneakerItem, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.figma)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = item.imageId),
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
            item.name?.let { Text(text = it, fontSize = 20.sp) }
            Text(text = "${item.price} USD", color = Color.Red, fontSize = 16.sp)
        }

        Image(
            painter = painterResource(id = R.drawable.change),
            contentDescription = "image",
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp)
                .clickable {
                    val sneakerItemString = Gson().toJson(item)
                    navController.navigate("changeSneaker/${sneakerItemString}")
                }
        )

        Image(
            painter = painterResource(id = R.drawable.trash),
            contentDescription = "image",
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp)
                .clickable {

                }
        )
    }
}



/*
@Composable
@Preview
fun CardSneakerLikePreview(){
    CardSneakerForChange(SneakerItem(R.drawable.sneaker, "Jordan", 159.99))
}*/
