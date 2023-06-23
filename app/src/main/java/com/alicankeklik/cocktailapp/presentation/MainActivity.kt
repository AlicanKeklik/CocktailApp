package com.alicankeklik.cocktailapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alicankeklik.cocktailapp.presentation.cocktaildetail.components.CocktailDetailScreen
import com.alicankeklik.cocktailapp.presentation.cocktails.components.CocktailScreen
import com.alicankeklik.cocktailapp.presentation.ui.CocktailAppTheme
import com.alicankeklik.cocktailapp.util.Constants.ID_DRINK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   val navController= rememberNavController()
                NavHost(navController = navController, startDestination = Screen.CocktailScreen.route){
                    composable(route= Screen.CocktailScreen.route){
                        CocktailScreen(navController = navController)
                    }
                    composable(route = Screen.CocktailDetailScreen.route +"/{${ID_DRINK}}"){
                      CocktailDetailScreen()
                    }
                }
                }
            }
        }
    }
}

