package com.example.cityapp.model.datasource

import android.content.Context
import com.example.cityapp.model.data.City
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

class CityDataSourceImpl @Inject constructor(
    private val context: Context
) : CityDataSource {

    override suspend fun loadCities(): List<City> {
        val jsonString = context.assets.open("cities.json").bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }
}
