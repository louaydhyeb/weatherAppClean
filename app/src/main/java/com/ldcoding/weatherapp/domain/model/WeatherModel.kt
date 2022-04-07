package com.ldcoding.weatherapp.domain.model

import com.ldcoding.weatherapp.data.remote.dto.*
import com.ldcoding.weatherapp.data.remote.dto.Weather

data class WeatherModel(
    val coord: Coord,
    val id: Int,
    val temperature: Double,
    val name: String,
    val weatherDescription: String
)
