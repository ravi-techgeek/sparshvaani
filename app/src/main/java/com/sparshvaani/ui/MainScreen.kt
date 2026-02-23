package com.sparshvaani.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class AppLanguage(val label: String, val tag: String) {
    ENGLISH("English", "en-US"),
    HINDI("Hindi", "hi-IN")
}

data class SpeechAction(
    val englishLabel: String,
    val hindiLabel: String,
    val englishSpeech: String,
    val hindiSpeech: String,
    val icon: ImageVector
)

val commonActions = listOf(
    SpeechAction("Water", "पानी", "I want water", "मुझे पानी चाहिए", Icons.Rounded.WaterDrop),
    SpeechAction("Food", "खाना", "I am hungry", "मुझे भूख लगी है", Icons.Rounded.Restaurant),
    SpeechAction("Toilet", "शौचालय", "I need to go to the toilet", "मुझे शौचालय जाना है", Icons.Rounded.Wc),
    SpeechAction("Pain", "दर्द", "I am in pain", "मुझे दर्द हो रहा है", Icons.Rounded.Sick),
    SpeechAction("Medicine", "दवा", "I need my medicine", "मुझे दवा चाहिए", Icons.Rounded.MedicalServices),
    SpeechAction("Sleep", "सोना", "I want to sleep", "मुझे सोना है", Icons.Rounded.Bedtime),
    SpeechAction("Happy", "खुश", "I am happy", "मैं खुश हूँ", Icons.Rounded.SentimentVerySatisfied),
    SpeechAction("Sad", "दुखी", "I am sad", "मैं दुखी हूँ", Icons.Rounded.SentimentVeryDissatisfied),
    SpeechAction("Yes", "हाँ", "Yes", "हाँ", Icons.Rounded.CheckCircle),
    SpeechAction("No", "नहीं", "No", "नहीं", Icons.Rounded.Cancel)
)

@Composable
fun LanguageSelectionScreen(onLanguageSelected: (AppLanguage) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Language / भाषा चुनें",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Button(
            onClick = { onLanguageSelected(AppLanguage.ENGLISH) },
            modifier = Modifier.fillMaxWidth(0.7f).height(60.dp)
        ) {
            Text("English", style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onLanguageSelected(AppLanguage.HINDI) },
            modifier = Modifier.fillMaxWidth(0.7f).height(60.dp)
        ) {
            Text("Hindi (हिंदी)", style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun MainScreen(language: AppLanguage, onActionClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = if (language == AppLanguage.HINDI) "स्पर्श वाणी" else "Sparsh Vaani",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(commonActions) { action ->
                val label = if (language == AppLanguage.HINDI) action.hindiLabel else action.englishLabel
                val speech = if (language == AppLanguage.HINDI) action.hindiSpeech else action.englishSpeech
                ActionCard(label = label, icon = action.icon, onClick = { onActionClick(speech) })
            }
        }
    }
}

@Composable
fun ActionCard(label: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}