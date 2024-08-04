package com.example.cityapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityapp.model.data.City
import com.example.cityapp.model.data.Trie
import com.example.cityapp.model.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    private val _filteredCities = MutableStateFlow<List<City>>(emptyList())
    val filteredCities: StateFlow<List<City>> = _filteredCities.asStateFlow()

    private val trie = Trie()

    init {
        viewModelScope.launch {
            val citiesList = cityRepository.getCities()
            _cities.value = citiesList
            citiesList.forEach { trie.insert(it) }
        }
    }

    fun searchCities(prefix: String) {
        _filteredCities.value = trie.search(prefix)
    }
}
