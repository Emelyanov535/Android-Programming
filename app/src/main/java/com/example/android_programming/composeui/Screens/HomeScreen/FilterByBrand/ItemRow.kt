package com.example.android_programming.composeui.Screens.HomeScreen.FilterByBrand

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_programming.R


@Composable
fun ItemRow(item: ItemFilterByBrand) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.figma))

    ) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(13.dp, 3.dp, 13.dp, 3.dp)
                .size(30.dp)
        )
    }
}

@Composable
@Preview
fun PreviewItemRow(){
    ItemRow(item = ItemFilterByBrand(R.drawable.jordan))
}