package com.ldcoding.weatherapp.presentation.loading_weather_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldcoding.weatherapp.common.Resource
import com.ldcoding.weatherapp.data.remote.dto.Coord
import com.ldcoding.weatherapp.domain.model.WeatherModel
import com.ldcoding.weatherapp.domain.use_case.get_weather.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    private var list = ArrayList<WeatherModel>()


    init {
        val listOfCities = listOf(
            Coord(
                48.117266, -1.6777926
            ), Coord(48.856614, 2.3522219), Coord(
                47.218371, -1.553621
            ), Coord(
                44.837789, -0.57918
            ), Coord(
                45.764043, 4.835659
            )
        )
        listOfCities.forEach {
            getWeather(it.lat.toString(), it.lon.toString())
        }

    }

    fun relaunch(){
        state.value.listCities = emptyList()
        list = ArrayList<WeatherModel>()
        viewModelScope.launch {
            val listOfCities = listOf(
                Coord(
                    48.117266, -1.6777926
                ), Coord(48.856614, 2.3522219), Coord(
                    47.218371, -1.553621
                ), Coord(
                    44.837789, -0.57918
                ), Coord(
                    45.764043, 4.835659
                )
            )
            listOfCities.forEach {
                getWeather(it.lat.toString(), it.lon.toString())
            }
        }
    }
    private fun getWeather(lat: String, lon: String) {
        getWeatherUseCase(lat, lon).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = WeatherState(weather = result.data)
                    list.add(result.data!!)
                    _state.value = WeatherState(listCities = list)
                }
                is Resource.Error -> {
                    _state.value =
                        WeatherState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = WeatherState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}