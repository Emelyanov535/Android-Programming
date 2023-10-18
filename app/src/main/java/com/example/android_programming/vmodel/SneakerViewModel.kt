package com.example.android_programming.vmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.android_programming.App
import com.example.android_programming.R
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.launch

class SneakerViewModel(val database: AppDatabase): ViewModel() {
    var brand = mutableStateOf("")
    val model = mutableStateOf("")
    val description = mutableStateOf("")
    val price = mutableStateOf("")
    val photo = mutableStateOf(R.drawable.img)
    val SneakerList = database.sneakerDao().getAllSneakers()
    var sneaker: Sneaker? = null

    fun insertSneaker() = viewModelScope.launch {
        val sneaker = Sneaker(
            brand = brand.value,
            model = model.value,
            description = description.value,
            price = price.value.toDouble(),
            photo = photo.value
            )
        database.sneakerDao().insert(sneaker)
    }

    fun deleteSneaker(sneaker :  Sneaker) = viewModelScope.launch {
        database.sneakerDao().delete(sneaker)
    }

    fun getSneakerById(id: Int) = viewModelScope.launch {
        database.sneakerDao().getSneakerById(id)
    }

    fun UpdateSneaker(sneaker: Sneaker) = viewModelScope.launch {
        database.sneakerDao().update(sneaker)
    }

    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return SneakerViewModel(database) as T
            }
        }
    }
}