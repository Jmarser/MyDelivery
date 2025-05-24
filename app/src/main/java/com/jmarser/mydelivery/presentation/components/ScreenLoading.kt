package com.jmarser.mydelivery.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.Orange_disabled
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun ScreenLoading(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.loading)
) {

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(200.dp),
                color = Orange_enabled,
                trackColor = Orange_disabled.copy(alpha = .7f)
            )
            SpacerHeightMedium()
            Text(
                text = message,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScreenLoadingPreview() {
    ScreenLoading(modifier = Modifier)
}
