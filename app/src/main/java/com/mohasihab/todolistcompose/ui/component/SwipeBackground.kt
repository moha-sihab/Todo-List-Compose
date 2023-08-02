package com.mohasihab.todolistcompose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.mohasihab.todolistcompose.R
import com.mohasihab.todolistcompose.ui.theme.Spacing
import com.mohasihab.todolistcompose.ui.theme.light_Green
import com.mohasihab.todolistcompose.ui.theme.md_theme_light_error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeBackground(dismissState: DismissState) {
    var color = Color.Transparent
    var arrangement = Arrangement.Start
    var textAction = ""

    when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> {
            color = md_theme_light_error
            arrangement = Arrangement.Start
            textAction = stringResource(id = R.string.delete)
        }

        DismissDirection.EndToStart -> {
            color = light_Green
            arrangement = Arrangement.End
            textAction = stringResource(id = R.string.done)
        }

        else -> {
            color = Color.Transparent
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = Spacing().medium, end = Spacing().medium, top = Spacing().small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = arrangement
    ) {
        Text(
            modifier = Modifier
                .padding(start = Spacing().medium, end = Spacing().medium),
            text = textAction,
            style = MaterialTheme.typography.headlineMedium.copy(color = color),
            softWrap = true,
        )

    }
}