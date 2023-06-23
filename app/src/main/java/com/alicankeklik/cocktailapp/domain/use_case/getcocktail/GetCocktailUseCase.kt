package com.alicankeklik.cocktailapp.domain.use_case.getcocktail

import com.alicankeklik.cocktailapp.data.remote.dto.toCocktail
import com.alicankeklik.cocktailapp.data.remote.dto.toCocktailList
import com.alicankeklik.cocktailapp.domain.model.Cocktail
import com.alicankeklik.cocktailapp.domain.repository.CocktailRepository
import com.alicankeklik.cocktailapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetCocktailUseCase @Inject constructor(private val repository: CocktailRepository) {
    fun executeGetCocktails(search: String): Flow<Resource<List<Cocktail>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val cocktailListDto = repository.getCocktails(search)
                if (cocktailListDto.drinks == null){
                    emit((Resource.Error("no founded search string ")))
                }else{
                    val cocktailList =  cocktailListDto.toCocktailList()
                    emit(Resource.Success(cocktailList.map { it.toCocktail() }))
                }
            } catch (e: HttpException) {
                emit((Resource.Error(e.localizedMessage?: "Error")))
            } catch (e:IOError){
                emit((Resource.Error("no internet connection")))
            }
        }
    }
}