package com.mohasihab.todolistcompose.ui.component

import androidx.compose.foundation.layout.Arrangement
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
fun EmptyData() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.9f),
            text = stringResource(R.string.no_data),
            style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onTertiary),
            textAlign = TextAlign.Center
        )

    }
}

@Preview
@Composable
fun EmptyDataPreview() {
    EmptyData()
}