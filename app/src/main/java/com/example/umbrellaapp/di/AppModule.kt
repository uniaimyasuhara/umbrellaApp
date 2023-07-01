package com.example.umbrellaapp.di

import com.example.umbrellaapp.data.repository.LocationRepositoryImpl
import com.example.umbrellaapp.data.repository.WeatherItemRepositoryImpl
import com.example.umbrellaapp.domain.repository.LocationRepository
import com.example.umbrellaapp.domain.repository.WeatherItemRepository
import com.example.umbrellaapp.data.datasource.remote.api.Constants.BASE_URL
import com.example.umbrellaapp.data.datasource.remote.api.Constants.LOCATION_BASE_URL
import com.example.umbrellaapp.data.datasource.remote.api.LocationApi
import com.example.umbrellaapp.data.datasource.remote.api.WeatherApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationApi(): LocationApi {
        return Retrofit.Builder()
            .baseUrl(LOCATION_BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(LocationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherItemRepository(api: WeatherApi) : WeatherItemRepository {
        return WeatherItemRepositoryImpl(api);
    }

    @Provides
    @Singleton
    fun provideLocationRepository(api: LocationApi) : LocationRepository {
        return LocationRepositoryImpl(api);
    }
}
