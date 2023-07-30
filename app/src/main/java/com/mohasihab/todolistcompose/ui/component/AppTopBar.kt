package com.mohasihab.todolistcompose.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(titleTopBar: String, backNav: Boolean = false, onNavClick: () -> Unit = {}) {
    TopAppBar(
        navigationIcon = {
            if (backNav) {
                IconButton(onClick = { onNavClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali"
                    )

                }
            }
        },
        title = { Text(titleTopBar) })
}