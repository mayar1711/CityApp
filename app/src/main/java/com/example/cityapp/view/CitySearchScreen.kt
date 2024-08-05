package com.example.cityapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.cityapp.model.data.City
import com.example.cityapp.viewmodel.CityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySearchScreen(viewModel: CityViewModel) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val filteredCities by viewModel.filteredCities.collectAsState()
    val context = LocalContext.current

    Column {
        SearchBar(query) { newQuery ->
            query = newQuery
            viewModel.searchCities(newQuery.text)
        }

        CityList(filteredCities) { city ->
            openCityInMaps(city, context)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: TextFieldValue, onQueryChanged: (TextFieldValue) -> Unit) {
    TextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Gray
        )
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
        supportingContent = { Text("Lon: ${city.coord.lon}, Lat: ${city.coord.lat}") }
    )
}

fun openCityInMaps(city: City, context: Context) {
    val uri = "geo:${city.coord.lat},${city.coord.lon}?q=${city.coord.lat},${city.coord.lon}(${city.name})"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(intent)
}
