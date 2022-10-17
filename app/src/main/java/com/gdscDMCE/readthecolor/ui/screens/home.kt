package com.gdscDMCE.readthecolor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdscDMCE.readthecolor.data.AnswerRecorderClient
import com.gdscDMCE.readthecolor.data.HomeScreenViewModel


@Composable
fun PuzzleText(colors:List<String>,actualColors:List<Color>){

    val coloredText = buildAnnotatedString {
        colors.forEachIndexed { i,ct->
            withStyle(SpanStyle(actualColors[i])){
                append("\t $ct \t")
            }
        }
    }

    Card(elevation = 12.dp, modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.7f)
        , shape = RoundedCornerShape(12.dp)
    ) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.7f)
            .offset(12.dp, 12.dp)
            ,text = coloredText, fontSize = 24.sp)

    }


}





@Composable
fun App(
    colorsTexts: List<String>,
    colors: List<Color>,
    inputState: String,
    startListening: () -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly,
        modifier =  Modifier.padding(12.dp)){
        PuzzleText(colorsTexts,colors)
        Button(onClick = startListening) {
            Text(text = inputState)
        }
    }
}

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    answerRecorderClient:AnswerRecorderClient,
    navController: NavController
){
    App(
        homeScreenViewModel.colorsTexts.value,
        homeScreenViewModel.colors.value,
        homeScreenViewModel. buttonText.value
    ){
        when(homeScreenViewModel.buttonText.value){
            "Speak" -> {
                homeScreenViewModel.buttonText.value = "Listening"
                answerRecorderClient.startListening()
            }
            "Listening" -> {
                homeScreenViewModel.buttonText.value = "Speak"
                answerRecorderClient.cancelListening()
            }

        }



    }
}