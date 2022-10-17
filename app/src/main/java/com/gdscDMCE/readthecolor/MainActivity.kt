package com.gdscDMCE.readthecolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gdscDMCE.readthecolor.ui.theme.ReadTheColorTheme
import com.gdscDMCE.readthecolor.data.*
import com.gdscDMCE.readthecolor.ui.screens.HomeScreen
import com.gdscDMCE.readthecolor.ui.screens.ResultScreen


class MainActivity : ComponentActivity() {
    private val homeScreenViewModel:HomeScreenViewModel by viewModels()
    private lateinit var answerRecorderClient:AnswerRecorderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO),9)

        setContent {
            ReadTheColorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                  answerRecorderClient =   AnswerRecorderClient(homeScreenViewModel, navController)
                    answerRecorderClient.init(this)

                    NavHost(navController, startDestination = "home"){
                        composable("home"){
                            HomeScreen(homeScreenViewModel, answerRecorderClient , navController )
                        }
                        composable("result/{result_text}", arguments = listOf(
                            navArgument("result_text"){
                                type  = NavType.StringType
                                nullable = true
                            }
                        )){
                            ResultScreen(
                                scoreText = it.arguments?.getString(
                                    "result_text"
                                ) ?: "")
                        }
                    }


                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        answerRecorderClient.dispose()
    }
}

