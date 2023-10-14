package com.example.android_programming.composeui.Screens.OrderScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.android_programming.composeui.Screens.LikeScreen.CardSneakerLike
import com.example.android_programming.model.SneakerItem

@Composable
fun ShoppingList(list : List<SneakerItem>) {
    Row {
        Column {
            for(item in list){
                CardSneakerLike(item = item)
            }
        }
    }
}