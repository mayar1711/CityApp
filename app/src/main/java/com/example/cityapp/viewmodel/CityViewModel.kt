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
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _filteredCities = MutableStateFlow<List<City>>(emptyList())
    val filteredCities: StateFlow<List<City>> = _filteredCities.asStateFlow()

    private val trie = Trie<City>()

    init {
        viewModelScope.launch {
            val citiesList = cityRepository.getCities().sortedWith(compareBy({ it.name }, { it.country }))
            citiesList.forEach { trie.insert(it.name.lowercase(Locale.ROOT), it) }
        }
    }

    fun searchCities(prefix: String) {
        _filteredCities.value = if (prefix.isEmpty()) {
            emptyList()
        } else {
            trie.search(prefix.lowercase(Locale.ROOT))
        }
    }
}
