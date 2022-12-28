package com.example.myboardgameapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myboardgameapplication.ui.BottomNavItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenView()
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Collection.screen_route) {
            composable(BottomNavItem.Collection.screen_route) {
                CollectionScreen()
            }
            composable(BottomNavItem.Event.screen_route) {
                EventScreen()
            }
            composable(BottomNavItem.Friend.screen_route) {
                FriendScreen()
            }

        }
    }
    val items = listOf(
        BottomNavItem.Collection,
        BottomNavItem.Event,
        BottomNavItem.Friend
    )

    @Composable
    fun BottomNavigation(navController: NavController) {
        Column(modifier = Modifier
            .fillMaxWidth().heightIn(min = 32.dp)
        ){
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = item.title) },
                        label = { Text(text = item.title,
                            fontSize = 9.sp) },
                        alwaysShowLabel = true,
                        selected = currentRoute == item.screen_route,
                        onClick = {
                            navController.navigate(item.screen_route) {
                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun MainScreenView(){
        val navController = rememberNavController()
        Scaffold(
            Modifier,
            bottomBar = { BottomNavigation(navController = navController) }

        )  { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding))
            NavigationGraph(navController = navController) }
    }
}