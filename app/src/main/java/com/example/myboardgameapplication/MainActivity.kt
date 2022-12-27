package com.example.myboardgameapplication

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myboardgameapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_collection, R.id.navigation_friend, R.id.navigation_event
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    @Composable
    fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = "profile"
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("profile") {
                ProfileScreen(
                    onNavigateToFriends = { navController.navigate("friendsList") },
                )
            }
        }
    }

    @Composable
    fun ProfileScreen(
        onNavigateToFriends: () -> Unit,
    ) {
        Button(onClick = onNavigateToFriends) {
            Text(text = "友達")
        }
    }

    @Composable
    fun CollectionScreen(
        onNavigateToFriends: () -> Unit,
    ) {
        Button(onClick = onNavigateToFriends) {
            Text(text = "コレクション")
        }
    }

    @Composable
    fun EventScreen(
        onNavigateToFriends: () -> Unit,
    ) {
        Button(onClick = onNavigateToFriends) {
            Text(text = "イベント")
        }
    }
}