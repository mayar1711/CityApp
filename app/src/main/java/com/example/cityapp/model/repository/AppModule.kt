package com.example.cityapp.model.repository

import android.content.Context
import com.example.cityapp.model.datasource.CityDataSource
import com.example.cityapp.model.datasource.CityDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun provideCityDataSource(cityDataSourceImpl: CityDataSourceImpl): CityDataSource

    @Singleton
    @Binds
    abstract fun provideCityRepository(cityDataSource: CityRepositoryImpl): CityRepository
}