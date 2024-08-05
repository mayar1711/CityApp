package com.example.cityapp.model.repository

import com.example.cityapp.model.data.City


interface CityRepository {
     suspend fun getCities(): List<City>
}
