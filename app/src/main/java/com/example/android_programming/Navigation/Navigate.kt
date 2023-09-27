package com.example.android_programming.Navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigate(){
    val navController = rememberNavController()
    val listItem = listOf(
        NavItem.Home,
        NavItem.Like,
        NavItem.Order,
        NavItem.Profile,
    )

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = Color.White
        ) {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentState = navBackStackEntry.value

            listItem.forEach { it ->
                val isSelected = currentState?.destination?.route == it.route

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        if(!isSelected){
                            navController.graph.startDestinationRoute?.let {
                                navController.popBackStack(it, inclusive = true)
                            }
                            navController.navigate(it.route){
                                launchSingleTop
                            }
                        }
                        navController.navigate(it.route)

                    },
                    icon = {
                        val iconModifier = if (isSelected) {
                            Modifier
                                .background(color = Color.LightGray, shape = CircleShape)
                                .padding(8.dp)
                        } else {
                            Modifier
                        }

                        Icon(
                            imageVector = it.icon,
                            contentDescription = null,
                            modifier = iconModifier.then(Modifier.size(24.dp))
                        )
                    }
                )
            }
        }
    }) {
        NavController(navController = navController)
    }
}

@Composable
@Preview
fun NavigatePreview(){
    Navigate()
}