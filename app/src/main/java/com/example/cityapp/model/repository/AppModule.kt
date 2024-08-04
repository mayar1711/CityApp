package com.example.cityapp.model.repository

import android.app.Application
import android.content.Context
import com.example.cityapp.model.datasource.CityDataSource
import com.example.cityapp.model.datasource.CityDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCityRepository(cityDataSource: CityDataSource): CityRepository {
        return CityRepositoryImpl(cityDataSource)
    }

    @Provides
    @Singleton
    fun provideCityDataSource(@ApplicationContext context: Context): CityDataSource {
        return CityDataSourceImpl(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ContextModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}