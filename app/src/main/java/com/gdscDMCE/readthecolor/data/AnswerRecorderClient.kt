package com.gdscDMCE.readthecolor.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.navigation.NavController

class AnswerRecorderClient(
    private val homeScreenViewModel: HomeScreenViewModel,
    private val navController: NavController
) : RecognitionListener{

    lateinit var  speechRecognizer : SpeechRecognizer


    fun init(context: Context){
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizer.setRecognitionListener(this)
    }

     val startListening = {
        val speechRec: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRec.putExtra(        RecognizerIntent.EXTRA_LANGUAGE
            ,java.util.Locale.getDefault())
        speechRec.putExtra(        RecognizerIntent.EXTRA_LANGUAGE_MODEL
            , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechRec.putExtra(RecognizerIntent.EXTRA_PROMPT , "Start Reading ")
        speechRecognizer.startListening(speechRec)
    }


    fun cancelListening(){
        speechRecognizer.cancel()
    }

    fun dispose(){
        speechRecognizer.destroy()
    }

    override fun onReadyForSpeech(p0: Bundle?) {

    }

    override fun onBeginningOfSpeech() {

    }

    override fun onRmsChanged(p0: Float) {

    }

    override fun onBufferReceived(p0: ByteArray?) {
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(p0: Int) {
    }

    override fun onResults(p0: Bundle?) {
        p0?.let {
            homeScreenViewModel.showResults(p0,navController)
            homeScreenViewModel.resetGame()
        }
    }

    override fun onPartialResults(p0: Bundle?) {
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }

}