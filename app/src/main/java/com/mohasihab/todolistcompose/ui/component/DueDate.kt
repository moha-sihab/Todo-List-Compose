package com.mohasihab.todolistcompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mohasihab.todolistcompose.R
import com.mohasihab.todolistcompose.ui.theme.Spacing
import com.mohasihab.todolistcompose.ui.theme.md_theme_light_secondary


@Composable
fun DueDate(
    modifier: Modifier = Modifier,
    dateDisplay: String = "",
    onClick: () -> Unit = {},
    contentColor : Color = md_theme_light_secondary
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(Spacing().small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.weight(0.1f),
            imageVector = Icons.Default.DateRange,
            contentDescription = stringResource(R.string.content_description_due_date),
            tint = contentColor
        )
        Text(
            modifier = Modifier.weight(0.9f),
            text = dateDisplay.ifEmpty { stringResource(R.string.set_due_date) },
            style = MaterialTheme.typography.labelMedium.copy(color = contentColor)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DueDatePreview() {
    DueDate(onClick = {})
}