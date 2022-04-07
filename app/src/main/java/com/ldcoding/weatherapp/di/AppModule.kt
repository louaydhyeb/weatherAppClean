package com.ldcoding.weatherapp.di

import com.ldcoding.weatherapp.common.Constants
import com.ldcoding.weatherapp.data.remote.OpenWeatherApi
import com.ldcoding.weatherapp.data.repository.WeatherRepositoryImpl
import com.ldcoding.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOpenWeatherApi() : OpenWeatherApi{
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherAppRepository(api: OpenWeatherApi): WeatherRepository{
        return WeatherRepositoryImpl(api)
    }

}