package com.example.android_programming.composeui.Navigation

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_programming.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigate() {
    val navController = rememberNavController()

    val Items = listOf(
        NavItem.Home,
        NavItem.Order,
        NavItem.Profile,
        NavItem.AdminPanel,
        NavItem.Report,
    )

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = Color.White
        ) {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentState = navBackStackEntry.value

            Items.forEach { it ->
                val isSelected = currentState?.destination?.route == it.route

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.graph.startDestinationRoute?.let {
                                navController.popBackStack(it, inclusive = true)
                            }
                            navController.navigate(it.route) {
                                launchSingleTop
                            }
                        }
                        navController.navigate(it.route)
                    },
                    icon = {
                        val iconModifier = if (isSelected) {
                            Modifier
                                .background(color = colorResource(id = R.color.figma_blue), shape = CircleShape)
                                .padding(8.dp)
                        } else {
                            Modifier
                        }

                        it.icon?.let { it1 ->
                            Icon(
                                imageVector = it1,
                                contentDescription = null,
                                tint = if (isSelected) Color.White else Color.Unspecified,
                                modifier = iconModifier.then(Modifier.size(24.dp))
                            )
                        }
                    }
                )
            }
        }
    }) {
        NavController(navController = navController)
    }
}
