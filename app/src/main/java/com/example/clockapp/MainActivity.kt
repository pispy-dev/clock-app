package com.example.clockapp

import android.os.Bundle
import android.widget.Space
import android.widget.TextClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.clockapp.ui.theme.ClockAppTheme
import java.util.TimeZone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClockAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    displayTxtClock()
                }
            }
        }
    }
}

@Composable
fun displayTxtClock() {
    Column(
        Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SingleClock(TimeZone.getDefault())
        Spacer(modifier = Modifier.height(16.dp))
        SingleClock(TimeZone.getTimeZone("America/New_York"))
        Spacer(modifier = Modifier.height(16.dp))
        SingleClock(TimeZone.getTimeZone("Europe/Zurich"))
        Spacer(modifier = Modifier.height(16.dp))
        SingleClock(TimeZone.getTimeZone("Asia/Hong_Kong"))
    }
}

@Composable
private fun SingleClock(
    timeZoneId: TimeZone,
) {
    Text(
        style = MaterialTheme.typography.bodyMedium,
        text = timeZoneId.displayName,
    )
    AndroidView(
        factory = { context ->
            TextClock(context).apply {
                format12Hour?.let { this.format12Hour = "hh:mm:ss a" }
                this.timeZone = timeZoneId.id
                textSize.let { this.textSize = 30f }
            }
        },
        modifier = Modifier.padding(5.dp),
    )
}

@Preview(
    showSystemUi = true,
)
@Composable
fun previewDisplayTxtClock() {
    displayTxtClock()
}