package com.example.cityapp.model.repository

import com.example.cityapp.model.data.City
import com.example.cityapp.model.datasource.CityDataSource
import javax.inject.Inject
import javax.inject.Singleton

class CityRepositoryImpl @Inject constructor(
    private val cityDataSource: CityDataSource
) : CityRepository {

    override suspend fun getCities(): List<City> {
        return cityDataSource.loadCities()
    }
}
