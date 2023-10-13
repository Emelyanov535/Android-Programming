package com.example.android_programming.Screens.AdminPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.android_programming.R
import com.example.android_programming.SneakerItem

@Composable
fun ChangePanel(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Row {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    listOf(
                        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                        SneakerItem(R.drawable.trash, "Nike", 179.99),
                    )
                ){_, item->
                    CardSneakerForChange(item = item, navHostController)

                }
            }
        }
    }
}