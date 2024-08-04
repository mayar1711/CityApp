package com.example.cityapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.cityapp.model.data.City
import com.example.cityapp.viewmodel.CityViewModel

@Composable
fun CitySearchScreen(viewModel: CityViewModel) {
    var query by remember { mutableStateOf("") }
    val filteredCities by viewModel.filteredCities.collectAsState()

    // Search Bar
    SearchBar(query) { newQuery ->
        query = newQuery
        viewModel.searchCities(newQuery)
    }

    // Display List
    CityList(filteredCities) { city ->
      //  openCityInMaps(city, LocalContext.current)
    }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        label = { Text("Search") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CityList(cities: List<City>, onCityClick: (City) -> Unit) {
    LazyColumn {
        items(cities) { city ->
            CityListItem(city, onCityClick)
        }
    }
}

@Composable
fun CityListItem(city: City, onCityClick: (City) -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onCityClick(city) },
        headlineContent = { Text("${city.name}, ${city.country}") },
        //supportingContent = { Text("Lon: ${city.coord.lon}, Lat: ${city.coord.lat}") }
    )
}

fun openCityInMaps(city: City, context: Context) {
  //  val uri = "geo:${city.coord.lat},${city.coord.lon}?q=${city.coord.lat},${city.coord.lon}(${city.name})"
  //  val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
 //   context.startActivity(intent)
}
