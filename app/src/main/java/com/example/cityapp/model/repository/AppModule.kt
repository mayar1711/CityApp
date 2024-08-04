package com.example.cityapp.model.repository

import android.content.Context
import com.example.cityapp.model.datasource.CityDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideCityDataSource(context: Context): CityDataSource
    @Binds
  abstract  fun provideCityRepository(cityDataSource: CityDataSource): CityRepository
}
