package com.ldcoding.weatherapp.data.repository

import com.ldcoding.weatherapp.data.remote.OpenWeatherApi
import com.ldcoding.weatherapp.data.remote.dto.WeatherDto
import com.ldcoding.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
) : WeatherRepository {

    override suspend fun getWeather(lat: String, lon: String): WeatherDto {
        return api.getWeather(lat,lon,"c467eea609100ad49d7b9f3dfbda41c7")
    }
}
