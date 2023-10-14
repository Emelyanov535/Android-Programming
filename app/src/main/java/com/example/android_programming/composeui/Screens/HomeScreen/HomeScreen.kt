package com.example.android_programming.composeui.Screens.HomeScreen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.android_programming.composeui.Screens.HomeScreen.FilterByBrand.ItemFilterByBrand
import com.example.android_programming.composeui.Screens.HomeScreen.FilterByBrand.ItemRow
import com.example.android_programming.composeui.Screens.HomeScreen.SearchField.SearchField
import com.example.android_programming.composeui.Screens.HomeScreen.SneakerRecyclerView.CardSneaker
import com.example.android_programming.R
import com.example.android_programming.composeui.Screens.HomeScreen.FilterByBrand.FilterByBrand
import com.example.android_programming.composeui.Screens.HomeScreen.SneakerRecyclerView.RecyclerView
import com.example.android_programming.model.getSneakers

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            // Поле для поиска
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 10.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) { searchText ->
                // Обработка введенного текста поиска
            }
        }
        Sales()
        FilterByBrand()
        RecyclerView(navHostController = navHostController)
    }
}