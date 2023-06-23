package com.alicankeklik.cocktailapp.data.remote.dto

import com.alicankeklik.cocktailapp.domain.model.Cocktail
import com.google.gson.annotations.SerializedName

data class CocktailListDto( @SerializedName("drinks" ) var drinks : ArrayList<CocktailDto> = arrayListOf()
)

fun CocktailListDto.toCocktailList(): List<CocktailDto> {
    return drinks.toList()
}

fun CocktailListDto.toCocktailDetail(): CocktailDto {

    return drinks.toList().get(0)
}