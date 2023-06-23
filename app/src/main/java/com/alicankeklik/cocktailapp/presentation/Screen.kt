package com.alicankeklik.cocktailapp.presentation

sealed class Screen(val route:String){
    object CocktailScreen  : Screen("cocktail_screen")
    object CocktailDetailScreen  : Screen("cocktail_detail_screen")

}