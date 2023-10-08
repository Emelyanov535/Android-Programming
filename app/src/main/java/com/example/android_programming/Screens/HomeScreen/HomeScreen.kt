package com.example.android_programming.Screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_programming.Screens.HomeScreen.BrandScrollBar.ItemFilterByBrand
import com.example.android_programming.Screens.HomeScreen.BrandScrollBar.ItemRow
import com.example.android_programming.Screens.HomeScreen.SearchField.SearchField
import com.example.android_programming.Screens.HomeScreen.SneakerRecyclerView.CardSneaker
import com.example.android_programming.SneakerItem
import com.example.android_programming.R

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row {
            // Поле для поиска
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) { searchText ->
                // Обработка введенного текста поиска
            }
        }

        Row {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 5.dp),

                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                itemsIndexed(
                    listOf(
                        ItemFilterByBrand(R.drawable.jordan),
                        ItemFilterByBrand(R.drawable.jordan),
                        ItemFilterByBrand(R.drawable.jordan),
                        ItemFilterByBrand(R.drawable.jordan)
                    )
                ){ _, item->
                    ItemRow(item = item)
                }
            }
        }

        Row {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val list = listOf(
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                    SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
                )

                // Определите количество столбцов
                val numColumns = 2

                // Разделите список на группы по numColumns элементов
                val chunkedList = list.chunked(numColumns)

                itemsIndexed(chunkedList) { _, chunkedListItem ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        for (item in chunkedListItem) {
                            // Создайте карточку для каждого элемента
                            CardSneaker(item = item, navController = navController)
                        }
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen()
}



