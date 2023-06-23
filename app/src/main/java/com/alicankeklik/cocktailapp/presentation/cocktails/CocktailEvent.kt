package com.alicankeklik.cocktailapp.presentation.cocktails

sealed class CocktailEvent{
    data class Search(val searchString: String):CocktailEvent()
}
