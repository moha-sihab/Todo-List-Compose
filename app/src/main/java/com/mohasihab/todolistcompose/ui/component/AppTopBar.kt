package com.mohasihab.todolistcompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mohasihab.todolistcompose.R
import com.mohasihab.todolistcompose.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    titleTopBar: String,
    backNav: Boolean = false,
    addTodo: Boolean = false,
    onNavClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
) {
    TopAppBar(
        navigationIcon = {
            if (backNav) {
                IconButton(onClick = { onNavClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_back)
                    )

                }
            }
        },
        title = { Text(titleTopBar) },
        actions = {
            if (addTodo) {
                Icon(
                    modifier = Modifier
                        .padding(end = Spacing().medium)
                        .clickable {
                            onCloseClick()
                        },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.content_description_close)
                )
                Icon(
                    modifier = Modifier
                        .padding(end = Spacing().small)
                        .clickable {
                            onSaveClick()
                        },
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.content_description_save)
                )
            }

        })
}