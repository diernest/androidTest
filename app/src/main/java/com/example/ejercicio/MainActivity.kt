package com.example.ejercicio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio.core.domain.repository.MovieRepository
import com.example.ejercicio.home.presentation.HomeScreen
import com.example.ejercicio.ui.theme.Background
import com.example.ejercicio.ui.theme.EjercicioTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /*@Inject
    lateinit var repository: MovieRepository*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjercicioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "HOME"){
                        composable("HOME"){
                            HomeScreen()
                        }
                    }

                    /*Greeting("Android")

                    lifecycleScope.launch {
                        val movies = repository.getUpcomingMovies()
                        println(movies)
                    }*/

                }
            }
        }
    }
}
