@file:OptIn(ExperimentalComposeUiApi::class)

package com.alicankeklik.cocktailapp.presentation.cocktails.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alicankeklik.cocktailapp.presentation.cocktails.CocktailEvent
import com.alicankeklik.cocktailapp.presentation.cocktails.CocktailViewModel
import com.alicankeklik.cocktailapp.presentation.Screen

@Composable
fun CocktailScreen(
    navController: NavController,
    //viewModel: MoviesViewModel = hiltViewModel()
    viewModel: CocktailViewModel =  hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Column(modifier = Modifier.fillMaxSize().alpha(if (state.isLoading) 1f else 0f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            Color.Blue,
                            radius = size.width / 2 - 5.dp.toPx() / 2,
                            style = Stroke(5.dp.toPx())
                        )
                    }
                    .alpha(if (state.isLoading) 1f else 0f)
                    ,
                color = Color.LightGray,
                strokeWidth = 5.dp
            )
        }

        Column() {
            CocktailSearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                hint = "apple",
                onSearch = {
                    viewModel.onEvent(CocktailEvent.Search(it))
                }

            )
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.cocktails){
                        cocktail ->
                    CocktailListItem(cocktail = cocktail, onItemClick = {
                        Log.e("CocktailScreen",""+Screen.CocktailScreen.route + "/${cocktail.idDrink}")
                        navController.navigate(Screen.CocktailDetailScreen.route + "/${cocktail.idDrink}")
                    })
                }
            }
        }

    }}
@Composable
fun CocktailSearchBar(
    modifier : Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        TextField(
            value = text,
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                onSearch(text)
            }),
            onValueChange = {
                text = it
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White)
            ,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}