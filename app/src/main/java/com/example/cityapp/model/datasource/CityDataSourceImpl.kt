package com.example.cityapp.model.datasource

import android.content.Context
import com.example.cityapp.model.data.City
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CityDataSourceImpl @Inject constructor(
    private val context: Context
) : CityDataSource {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val cities: List<City> by lazy {
        val jsonString = context.assets.open("cities.json").bufferedReader().use { it.readText() }
        json.decodeFromString(jsonString)
    }

    override suspend fun loadCities(page: Int, pageSize: Int): List<City> {
        val fromIndex = (page - 1) * pageSize
        val toIndex = kotlin.math.min(fromIndex + pageSize, cities.size)
        return if (fromIndex < cities.size) {
            cities.subList(fromIndex, toIndex)
        } else {
            emptyList()
        }
    }
}
