package com.jmarser.mydelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.mydelivery.ui.theme.MyDeliveryTheme
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.utilities.MyLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val windowSize = calculateWindowSizeClass(this)

            MyDeliveryTheme (
                windowSize = windowSize.widthSizeClass
            ){

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "DisplayLarge",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(MyDimens.dimens.spacerMedium))
        Text(
            text = "HeadLineLarge",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "TitleLarge",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "BodyLarge",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "LabelLarge",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDeliveryTheme {
        Greeting()
    }
}