package com.example.android_programming.composeui.Screens.HomeScreen.FilterByBrand

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_programming.R
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.SneakerViewModel

@Preview
@Composable
fun FilterByBrand(sneakerViewModel: SneakerViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Row {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 5.dp),

            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            itemsIndexed(
                listOf(
                    ItemFilterByBrand(R.drawable.jordan){
                        sneakerViewModel.searchSneakersByNike()
                    },
                    ItemFilterByBrand(R.drawable.adidas){
                        sneakerViewModel.searchSneakersByAdidas()
                    },
                    ItemFilterByBrand(R.drawable.jordan){
                        sneakerViewModel.searchSneakersByNike()
                    },
                    ItemFilterByBrand(R.drawable.adidas){
                        sneakerViewModel.searchSneakersByAdidas()
                    },
                )
            ){ _, item->
                ItemRow(item = item)
            }
        }
    }
}