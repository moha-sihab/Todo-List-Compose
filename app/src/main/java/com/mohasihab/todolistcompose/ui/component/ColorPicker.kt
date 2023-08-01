package com.mohasihab.todolistcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mohasihab.todolistcompose.core.utils.toTaskColor
import com.mohasihab.todolistcompose.ui.theme.Spacing

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    val listOfColor = listOf("blue", "orange", "green")
    listOfColor.forEach {
        val cardColor = it.toTaskColor()
        Box(
            modifier = Modifier
                .size(30.dp)
                .shadow(15.dp, CircleShape)
                .clip(CircleShape)
                .background(cardColor.containerColor)
                .border(
                    width = 3.dp,
                    color = Color.White,
                    shape = CircleShape
                )
                .clickable {
                    onClick(it)
                }
        )
    }
}