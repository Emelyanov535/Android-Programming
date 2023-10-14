package com.example.android_programming.composeui.Screens.LikeScreen;

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_programming.R
import com.example.android_programming.model.SneakerItem

@Composable
fun LikeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Text(
            text = "Favorites",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp, 10.dp)
        )
        Row {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    listOf(
                        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    )
                ){_, item->
                        CardSneakerLike(item = item)

                }
            }
        }
    }
}

@Composable
@Preview
fun LikeScreenPreview(){
    LikeScreen()
}
