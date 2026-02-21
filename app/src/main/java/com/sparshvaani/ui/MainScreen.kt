package com.sparshvaani.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class SpeechAction(val label: String, val speechText: String)

val commonActions = listOf(
    SpeechAction("पानी (Water)", "मुझे पानी चाहिए"),
    SpeechAction("खाना (Food)", "मुझे भूख लगी है"),
    SpeechAction("शौचालय (Toilet)", "मुझे शौचालय जाना है"),
    SpeechAction("दर्द (Pain)", "मुझे दर्द हो रहा है"),
    SpeechAction("दवा (Medicine)", "मुझे दवा चाहिए"),
    SpeechAction("सोना (Sleep)", "मुझे सोना है"),
    SpeechAction("खुश (Happy)", "मैं खुश हूँ"),
    SpeechAction("दुखी (Sad)", "मैं दुखी हूँ"),
    SpeechAction("हाँ (Yes)", "हाँ"),
    SpeechAction("नहीं (No)", "नहीं")
)

@Composable
fun MainScreen(onActionClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "स्पर्श वाणी (Sparsh Vaani)",
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
                ActionCard(action = action, onClick = { onActionClick(action.speechText) })
            }
        }
    }
}

@Composable
fun ActionCard(action: SpeechAction, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            Text(
                text = action.label,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}