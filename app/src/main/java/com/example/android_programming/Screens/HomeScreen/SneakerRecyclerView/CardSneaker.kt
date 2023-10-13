package com.example.android_programming.Screens.HomeScreen.SneakerRecyclerView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_programming.R
import com.example.android_programming.Screens.HomeScreen.AboutSneaker
import com.example.android_programming.SneakerItem
import com.google.gson.Gson

@Composable
fun CardSneaker(item: SneakerItem, navController: NavHostController) {
    val maxWidth = (LocalConfiguration.current.screenWidthDp / 2).dp

    Box(
        modifier = Modifier
            .padding(4.dp)
            .widthIn(maxWidth)
            .clickable {
                val sneakerItemString = Gson().toJson(item)
                navController.navigate("aboutSneaker/${sneakerItemString}")
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.figma))
                .widthIn(maxWidth)
        ) {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = "image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(100.dp)
            )

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
                        item.name?.let { Text(text = it) }
                        Text(text = item.price.toString(), color = Color.Red)
                    }
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(40.dp, 20.dp)
                        ) {
                            Text(text = "+", fontSize = 4.sp)
                        }
                    }
                }
            }
        }
    }
}
