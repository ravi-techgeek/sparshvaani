package com.sparshvaani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sparshvaani.ui.MainScreen
import com.sparshvaani.ui.theme.SparshvaaniTheme

class MainActivity : ComponentActivity() {
    private lateinit var ttsHelper: TTSHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        ttsHelper = TTSHelper(this)
        
        enableEdgeToEdge()
        setContent {
            SparshvaaniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainScreen(onActionClick = { textToSpeak ->
                            ttsHelper.speak(textToSpeak)
                        })
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsHelper.shutdown()
    }
}