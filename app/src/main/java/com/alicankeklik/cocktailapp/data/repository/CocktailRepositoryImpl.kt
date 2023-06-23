package com.alicankeklik.cocktailapp.data.repository

import com.alicankeklik.cocktailapp.data.remote.dto.CocktailAppApi
import com.alicankeklik.cocktailapp.data.remote.dto.CocktailDto
import com.alicankeklik.cocktailapp.data.remote.dto.CocktailListDto
import com.alicankeklik.cocktailapp.domain.repository.CocktailRepository
import javax.inject.Inject


class CocktailRepositoryImpl @Inject constructor(
    private val api: CocktailAppApi
) : CocktailRepository{
    override suspend fun getCocktails(search :String): CocktailListDto {
        return api.getCocktail(search)
    }

    override suspend fun getCocktailDetail(cocktailId:String): CocktailListDto {
        return api.getCocktailDetail(cocktailId)
    }

}