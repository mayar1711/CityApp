package com.example.cityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.cityapp.ui.theme.CityAppTheme
import com.example.cityapp.view.CitySearchScreen
import com.example.cityapp.viewmodel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private lateinit var  cityViewModel : CityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityViewModel  = ViewModelProvider(this)[CityViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            CityAppTheme {
                    CitySearchScreen(viewModel = cityViewModel)
            }
        }
    }
}


