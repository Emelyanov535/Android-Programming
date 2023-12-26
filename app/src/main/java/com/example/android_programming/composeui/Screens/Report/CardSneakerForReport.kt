package com.example.android_programming.composeui.Screens.Report

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android_programming.R
import com.example.android_programming.api.model.RemoteConverters
import com.example.android_programming.api.model.SneakerCountPair
import com.example.android_programming.model.BasketSneakers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun CardSneakerForReport(item: SneakerCountPair) {
    val maxWidth = (LocalConfiguration.current.screenWidthDp / 2).dp
    Box(
        modifier = Modifier
            .padding(4.dp)
            .widthIn(maxWidth)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.figma))
                .widthIn(maxWidth)
        ) {
            Image(
                bitmap = RemoteConverters.toBitmap(item.sneaker.photo).asImageBitmap(),
                contentDescription = "image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(100.dp)
            )
        }

        Column {
            Row(
                modifier = Modifier
                    .widthIn(maxWidth),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                ) {
                    Row {
                        item.sneaker.brand?.let { Text(text = "$it ") }
                        item.sneaker.model?.let { Text(text = it) }
                    }
                    Text(text = item.sneaker.price.toString(), color = Color.Red)
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .background(color = Color.Red, shape = CircleShape)
        ) {
            Text(
                text = "x${item.quantity}",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}





