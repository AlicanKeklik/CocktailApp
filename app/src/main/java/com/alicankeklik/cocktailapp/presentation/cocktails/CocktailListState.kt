package com.alicankeklik.cocktailapp.presentation.cocktails

import com.alicankeklik.cocktailapp.domain.model.Cocktail

data class CocktailListState(
    val isLoading:Boolean=false,
    val cocktails:List<Cocktail> = emptyList(),
    val error: String ="",
    val search: String = "apple"
)
