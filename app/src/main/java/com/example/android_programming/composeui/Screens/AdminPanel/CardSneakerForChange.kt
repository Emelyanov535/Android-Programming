package com.example.android_programming.composeui.Screens.AdminPanel

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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.R
import com.example.android_programming.model.Sneaker
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.SneakerViewModel
import com.google.gson.Gson

@Composable
fun CardSneakerForChange(item: Sneaker, navController: NavHostController, sneakerViewModel: SneakerViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.figma)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            bitmap = item.photo.asImageBitmap(),
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
            Text(text = "${item.price} $", color = Color.Red, fontSize = 16.sp)
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.figma_blue),
                contentColor = Color.White
            ),
            onClick = {
                sneakerViewModel.brand.value = item.brand ?: ""
                sneakerViewModel.model.value = item.model ?: ""
                sneakerViewModel.description.value = item.description ?: ""
                sneakerViewModel.price.value = item.price.toString()
                val sneakerItemString = Gson().toJson(item)
                navController.navigate("changeSneaker/${sneakerItemString}") },
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Create, contentDescription = "change")
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.figma_blue),
                contentColor = Color.White
            ),
            onClick = {
                  sneakerViewModel.deleteSneaker(item)
            },
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
        }
    }
}
