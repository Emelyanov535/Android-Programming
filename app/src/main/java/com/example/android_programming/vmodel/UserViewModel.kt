package com.example.android_programming.vmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.android_programming.App
import com.example.android_programming.GlobalUser
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.model.Basket
import com.example.android_programming.model.RoleEnum
import com.example.android_programming.model.User
import com.example.android_programming.repository.BasketRepository
import com.example.android_programming.repository.SneakerRepository
import com.example.android_programming.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {

    var name = mutableStateOf("")
    val surname = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    fun createUser() = viewModelScope.launch {
        val user = User(
            name = name.value,
            surname = surname.value,
            email = email.value,
            password = password.value,
            role = RoleEnum.User
        )
        userRepository.createUser(user)
    }
    fun authUser() = viewModelScope.launch {
        val user = userRepository.getUserByEmail(email.value)
        if (password.value != "" && user.password == password.value) {
            val globalUser = GlobalUser.getInstance()
            globalUser.setUser(user)
        }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}