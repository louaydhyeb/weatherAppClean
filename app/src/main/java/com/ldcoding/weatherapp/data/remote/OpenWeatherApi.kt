package com.ldcoding.weatherapp.data.remote

import com.ldcoding.weatherapp.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getWeather(@Query("lat") lat: String, @Query("lon") lon: String,@Query("appid") appId: String): WeatherDto
}