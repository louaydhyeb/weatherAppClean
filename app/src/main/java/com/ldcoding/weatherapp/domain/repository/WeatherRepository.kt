package com.ldcoding.weatherapp.domain.repository

import com.ldcoding.weatherapp.data.remote.dto.WeatherDto

interface WeatherRepository {
    suspend fun getWeather(lat: String,lon: String): WeatherDto
}