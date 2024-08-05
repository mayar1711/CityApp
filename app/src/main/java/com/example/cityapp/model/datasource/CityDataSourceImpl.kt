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
    override suspend fun getCities(): List<City> {
        val inputStream = context.assets.open("cities.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }

}
