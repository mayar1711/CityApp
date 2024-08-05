package com.example.cityapp.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class City(
    val country: String,
    val name: String,
    @SerialName("_id") val id: Int?,
    val coord: Coord
)

@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)
