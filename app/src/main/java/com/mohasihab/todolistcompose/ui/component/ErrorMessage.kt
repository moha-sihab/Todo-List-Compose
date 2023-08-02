package com.mohasihab.todolistcompose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mohasihab.todolistcompose.R

@Composable
fun ErrorData(message : String) {
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(R.string.error),
            style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onTertiary),
            textAlign = TextAlign.Center
        )
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onTertiary),
            textAlign = TextAlign.Center
        )
    }
    
}

@Preview
@Composable
fun ErrorDataPreview() {
      ErrorData(message = "error sub title")
}