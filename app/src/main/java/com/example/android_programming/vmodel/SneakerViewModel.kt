package com.example.android_programming.vmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.android_programming.App
import com.example.android_programming.R
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.model.Sneaker
import com.example.android_programming.repository.SneakerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SneakerViewModel(private val sneakerRepository: SneakerRepository): ViewModel() {
    var brand = mutableStateOf("")
    val model = mutableStateOf("")
    val description = mutableStateOf("")
    val price = mutableStateOf("")
    val photo = mutableIntStateOf(R.drawable.img)
    val sneakerList = sneakerRepository.call().cachedIn(viewModelScope)
    var sneaker: Sneaker? = null

    fun insertSneaker() = viewModelScope.launch {
        val sneaker = Sneaker(
            brand = brand.value,
            model = model.value,
            description = description.value,
            price = price.value.toDouble(),
            photo = photo.value
            )
        sneakerRepository.insertSneaker(sneaker)
    }

    fun deleteSneaker(sneaker :  Sneaker) = viewModelScope.launch {
        sneakerRepository.deleteSneaker(sneaker)
    }

    fun getSneakerById(id: Int) = viewModelScope.launch {
        sneakerRepository.getSneakerById(id)
    }

    fun UpdateSneaker(sneaker: Sneaker) = viewModelScope.launch {
        sneakerRepository.updateSneaker(sneaker)
    }
}