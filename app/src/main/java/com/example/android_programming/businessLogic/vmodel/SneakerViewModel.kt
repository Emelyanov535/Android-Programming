package com.example.android_programming.businessLogic.vmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.R
import com.example.android_programming.api.repository.RestSneakerRepository
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SneakerViewModel(private val sneakerRepository: RestSneakerRepository): ViewModel() {
    val sneakerList = sneakerRepository.getAllSneakers()
    var brand = mutableStateOf("")
    val model = mutableStateOf("")
    val description = mutableStateOf("")
    val price = mutableStateOf("")
    val photo = mutableIntStateOf(R.drawable.img)
    var sneaker: Sneaker? = null
    private var _record = MutableStateFlow<Sneaker?>(null)
    var record: StateFlow<Sneaker?> = _record
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

    fun UpdateSneaker(sneaker: Sneaker) = viewModelScope.launch {
        sneakerRepository.updateSneaker(sneaker)
    }
}