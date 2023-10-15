package com.example.android_programming.model

import android.util.Log
import com.example.android_programming.R

class PhotoManager {
    private val photos = listOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
    )

    private var currentIndex = 0

    fun changePhoto(int: Int): Int {
        currentIndex = photos.indexOf(int)
        if (photos.isNotEmpty()) {
            if(currentIndex + 1 >= photos.size){
                currentIndex = 0
            }else{
                currentIndex += 1
            }
            return photos[currentIndex]
        } else {
            return -1
        }
    }
}





