package com.ldcoding.weatherapp.data.remote.dto

import com.ldcoding.weatherapp.domain.model.WeatherModel

data class WeatherDto(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun WeatherDto.toWeather(): WeatherModel {
    return WeatherModel(
        id = id,
        name = name,
        temperature = main.temp,
        coord = coord,
        weatherDescription = weather[0].main


    )
}