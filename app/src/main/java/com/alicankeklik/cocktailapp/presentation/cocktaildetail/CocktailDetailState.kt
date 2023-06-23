package com.alicankeklik.cocktailapp.presentation.cocktaildetail

import com.alicankeklik.cocktailapp.domain.model.Cocktail
import com.alicankeklik.cocktailapp.domain.model.CocktailDetail

data class CocktailDetailState(
    val isLoading:Boolean=false,
    val cocktails:CocktailDetail? = null,
    val error: String =""
)
