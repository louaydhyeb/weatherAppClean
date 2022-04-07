package com.ldcoding.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ldcoding.weatherapp.presentation.loading_weather_screen.components.WeatherListScreen
import com.ldcoding.weatherapp.presentation.main_screen.WeatherMainScreen
import com.ldcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WeatherMainScreen.route
                    ) {
                        composable(
                            route = Screen.WeatherMainScreen.route
                        ) {
                            WeatherMainScreen(navController)
                        }
                        composable(
                            route = Screen.WeatherDetailScreen.route
                        ) {
                            WeatherListScreen()
                        }

                    }
                }
            }
        }
    }
}

