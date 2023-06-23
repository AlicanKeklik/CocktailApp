package com.alicankeklik.cocktailapp.presentation.cocktaildetail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicankeklik.cocktailapp.domain.use_case.getcocktaildetail.GetCocktailDetailUseCase
import com.alicankeklik.cocktailapp.util.Constants.ID_DRINK
import com.alicankeklik.cocktailapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel()
class CocktailDetailViewModel @Inject constructor(
    private val getCocktailDetailUseCase: GetCocktailDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) :ViewModel() {

    private val _state = mutableStateOf<CocktailDetailState>(CocktailDetailState())
    val state:State<CocktailDetailState> = _state


    init {
        savedStateHandle.get<String>(ID_DRINK).let {
            it?.let { it1 -> getCocktailDetail(it1)
                Log.e("CocktailScreen","${it1}") }

        }
    }

    private fun getCocktailDetail(coctailId :String ) {
        getCocktailDetailUseCase.executeGetCocktailDetail(coctailId).onEach {
            when (it) {
                is Resource.Success -> {
                    Log.e("cock1","${it.data?.strDrink}")

                    _state.value = CocktailDetailState(cocktails = it.data)

                }
                is Resource.Loading -> {
                    _state.value = CocktailDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CocktailDetailState(error = it.message ?: "An uxexpected error occured")

                }
            }
        }.launchIn(viewModelScope)
    }
}

