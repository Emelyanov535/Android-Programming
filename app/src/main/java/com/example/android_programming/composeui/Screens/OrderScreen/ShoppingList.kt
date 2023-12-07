package com.example.android_programming.composeui.Screens.OrderScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.android_programming.model.Sneaker
import com.example.android_programming.businessLogic.vmodel.OrderViewModel

@Composable
fun ShoppingList(list : List<Sneaker>) {
    Row {
        Column {
            for(item in list){
                CardSneakerLike(item = item)
            }
        }
    }
}