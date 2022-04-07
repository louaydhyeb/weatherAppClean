package com.ldcoding.weatherapp.presentation

sealed class Screen(val route: String) {
    object WeatherDetailScreen: Screen("weather_detail_screen")
    object WeatherMainScreen: Screen("weather_main_screen")
}