package com.ldcoding.weatherapp.presentation.loading_weather_screen

import com.ldcoding.weatherapp.domain.model.WeatherModel

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: WeatherModel? = null,
    val error: String = "",
    var listCities: List<WeatherModel> = emptyList()
)
