package com.example.android_programming.composeui.Screens.Report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.ReportViewModel


@Composable
fun ReportScreen(reportViewModel: ReportViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        if(reportViewModel.countOrder.value == 0){
            ReportCard()
        }else{
            val sneakerList by reportViewModel.sneakerList.observeAsState()
            val orderList by reportViewModel.orderList.observeAsState()
            LazyColumn(modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 50.dp)
                .fillMaxSize()
                .background(Color.White),
            ) {
                item{
                    CardForTextData()
                    Text(
                        text = "Самые заказываемые кроссовки",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    LazyRow {
                        itemsIndexed(sneakerList ?: emptyList()) { index, sneaker ->
                            CardSneakerForReport(sneaker)
                        }
                    }
                    Text(
                        text = "Заказы за период",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                item{
                    Column() {
                        orderList?.forEach { order ->
                            CardOrderForReport(item = order)
                        }
                    }
                }
            }
        }
    }
}