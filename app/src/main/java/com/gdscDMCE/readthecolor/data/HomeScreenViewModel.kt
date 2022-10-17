package com.gdscDMCE.readthecolor.data

import android.os.Bundle
import android.speech.SpeechRecognizer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class HomeScreenViewModel : ViewModel() {
    fun showResults(p0: Bundle,navController: NavController) {
       val ans = buildString {
//            withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)){
                append(" Score: ")
//            }
//            withStyle(SpanStyle(fontWeight = FontWeight.Thin)){
              val  score = calculateScore(colors.value,
                    p0.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)!![0].split(Regex("\\s+")))
                append("$score / $WORLD_LEN")
//            }

        }

        showAlertDialog.value = true

        buttonText.value  = "Speak"

        navController.navigate("result/${ans}")
    }

    val  ans  = mutableStateOf<AnnotatedString>(buildAnnotatedString {  })

    val initialExpanded =  COLORS.toList().expand(WORLD_LEN)

    val colorsTexts  = mutableStateOf(initialExpanded)

    val colors  = mutableStateOf(initialExpanded.map{ it.toColor()}.shuffled())

    val showAlertDialog = mutableStateOf(false)
    val buttonText = mutableStateOf("Speak")



     fun resetGame() {
        colorsTexts.value =  COLORS.toList().expand(WORLD_LEN)
        colors.value =  colorsTexts.value.map{ it.toColor()}.shuffled()
    }


}