package com.sparshvaani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sparshvaani.ui.AppLanguage
import com.sparshvaani.ui.LanguageSelectionScreen
import com.sparshvaani.ui.MainScreen
import com.sparshvaani.ui.theme.SparshvaaniTheme
import java.util.Locale

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
                        var selectedLanguage by remember { mutableStateOf<AppLanguage?>(null) }

                        if (selectedLanguage == null) {
                            LanguageSelectionScreen { language ->
                                selectedLanguage = language
                                ttsHelper.setLanguage(Locale.forLanguageTag(language.tag))
                            }
                        } else {
                            MainScreen(language = selectedLanguage!!, onActionClick = { textToSpeak ->
                                ttsHelper.speak(textToSpeak)
                            })
                        }
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