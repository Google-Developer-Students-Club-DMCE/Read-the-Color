package com.gdscDMCE.readthecolor.data

import android.util.Log
import androidx.compose.ui.graphics.Color
import java.util.*
import kotlin.math.min


val COLORS = arrayOf("Red","Green","Blue","Yellow","Black")

 fun String.toColor(): Color {
    return when(this){
        "Red" -> Color.Red
        "Green" -> Color.Green
        "Blue" -> Color.Blue
        "Yellow" -> Color.Yellow
        else -> Color.Black
    }
}

 fun Color.toName():String{
    return when(this){
        Color.Red   ->"Red"
        Color.Green ->  "Green"
        Color.Blue  -> "Blue"
        Color.Yellow->   "Yellow"
        else -> "black"
    }
}

const  val WORLD_LEN = 10


 fun List<String>.expand(length:Int): List<String>{
    val expanded  = mutableListOf<String>()
    for (i in (0 until length)){
        expanded.add(random())
    }
    return expanded
}

 fun calculateScore(colors: List<Color>, answeredColors: List<String>): Int {

    var score = 0
    for ( i in (0 until min(answeredColors.size,colors.size))){

        Log.i("Omkar", "calculateScore: ${colors[i].toName().lowercase(Locale.getDefault())} == ${answeredColors[i].lowercase(
            Locale.getDefault()
        )}")
        if(colors[i].toName().lowercase(Locale.getDefault()).contentEquals(answeredColors[i].lowercase(
                Locale.getDefault()
            ))){
            score ++
        }
    }
    return score
}
