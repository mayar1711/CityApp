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

    override suspend fun loadCities(): List<City> {
        val jsonString = context.assets.open("cities.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(jsonString)
    }
}
