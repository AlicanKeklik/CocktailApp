package com.alicankeklik.cocktailapp.presentation.cocktaildetail.components

import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alicankeklik.cocktailapp.presentation.cocktaildetail.CocktailDetailViewModel

@Composable
fun  CocktailDetailScreen(cocktailDetailViewModel: CocktailDetailViewModel = hiltViewModel()){
    val state = cocktailDetailViewModel.state.value
    val color = remember { Animatable(Color.Gray) }
    LaunchedEffect(Unit) {
        color.animateTo(Color.DarkGray, animationSpec = tween(1000))
        color.animateTo(Color.Black, animationSpec = tween(1000))
    }

    Box(modifier = Modifier
            .fillMaxSize()
            .background(color.value), contentAlignment = Alignment.Center){
        Column(modifier = Modifier.fillMaxSize()
            .alpha(if (state.isLoading) 1f else 0f),
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
        state.cocktails?.let {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = it.strDrink ?: "null",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(14.dp)
                        .alpha(if (it.strDrink == null) 0f else 1f),
                    color = Color.White)
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .background(Color.White))
                AsyncImage(model = it.strDrinkThumb ?: "null", contentDescription =it.strDrink, modifier = Modifier
                    .padding(16.dp)
                    .size(200.dp, 200.dp)
                    .clip(RectangleShape)
                    .align(Alignment.CenterHorizontally)
                    .alpha(if (it.strDrinkThumb == null) 0f else 1f))

                Log.e("cock","${it.strDrink}")
                Text(text = it.strInstructions ?: "",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(14.dp)
                        .alpha(if (it.strInstructions == null) 0f else 1f),
                    color = Color.White)
                Text(text = it.strIngredient1 ?: "",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(14.dp)
                        .alpha(if (it.strIngredient1 == null) 0f else 1f),
                    color = Color.White)
                Text(text = it.strIngredient2 ?: "",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(14.dp)
                        .alpha(if (it.strIngredient2 == null) 0f else 1f),
                    color = Color.White)
                Text(text = it.strIngredient3 ?: "",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(14.dp)
                        .alpha(if (it.strIngredient3 == null) 0f else 1f),
                    color = Color.White)
                Text(text = it.strIngredient4 ?: "",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(14.dp)
                        .alpha(if (it.strIngredient4 == null) 0f else 1f),
                    color = Color.White)
            }


        }

    }

}