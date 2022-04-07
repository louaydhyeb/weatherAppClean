package com.ldcoding.weatherapp.domain.use_case.get_weather

import com.ldcoding.weatherapp.common.Resource
import com.ldcoding.weatherapp.data.remote.dto.WeatherDto
import com.ldcoding.weatherapp.data.remote.dto.toWeather
import com.ldcoding.weatherapp.domain.model.WeatherModel
import com.ldcoding.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    @OptIn(ExperimentalTime::class)
    operator fun invoke(lat:String, lon:String): Flow<Resource<WeatherModel>> = flow{
        try {
            emit(Resource.Loading<WeatherModel>())
            delay(Duration.seconds(60))
            val weather = repository.getWeather(lat,lon).toWeather()
            emit(Resource.Success<WeatherModel>(weather))
        }catch (e:HttpException){
         emit(Resource.Error<WeatherModel>(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e : IOException){
            emit(Resource.Error<WeatherModel>("Coundn't reach server, check ur internet"))
        }
    }
}