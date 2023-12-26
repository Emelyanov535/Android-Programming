package com.example.android_programming.composeui.Screens.Report

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_programming.R
import com.example.android_programming.api.model.ExpandedOrderRemote
import com.example.android_programming.api.model.toSneaker
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.OrderViewModel
import com.example.android_programming.businessLogic.vmodel.ReportViewModel
import com.example.android_programming.model.Order
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun CardOrderForReport(item: ExpandedOrderRemote){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.figma))
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ){
            Text("№ ${item.id}")
            Text("${item.date}")
            Text("${item.user}")

            Row(){
                for(sneaker in item.sneakerList){
                    Image(
                        contentScale = ContentScale.FillBounds,
                        bitmap = sneaker.toSneaker().photo.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .padding(0.dp, 10.dp, 10.dp, 10.dp)
                    )
                }
            }
        }
    }
}