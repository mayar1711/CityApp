package com.example.cityapp.model.datasource

import com.example.cityapp.model.data.City

interface CityDataSource {
    suspend fun loadCities(): List<City>
}
