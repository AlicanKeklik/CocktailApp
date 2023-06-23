package com.alicankeklik.cocktailapp.domain.repository

import com.alicankeklik.cocktailapp.data.remote.dto.CocktailDto
import com.alicankeklik.cocktailapp.data.remote.dto.CocktailListDto
import com.alicankeklik.cocktailapp.domain.model.CocktailDetail

interface CocktailRepository {
    suspend fun getCocktails(search:String):CocktailListDto
    suspend fun getCocktailDetail(cocktailId:String):CocktailListDto
}