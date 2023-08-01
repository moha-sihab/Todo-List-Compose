package com.mohasihab.todolistcompose.core.utils

import androidx.compose.ui.graphics.Color
import com.mohasihab.todolistcompose.core.domain.mapper.toTaskDisplay
import com.mohasihab.todolistcompose.core.domain.model.CardColorModel

fun String.toTaskColor() : CardColorModel {
    var cardColorModel =
        CardColorModel(contentColor = Color.Black, containerColor = Color.White)
    val blueContainerColor = Color(0xFF1B60A5)
    val blueContentColor = Color(0xFFD4E3FF)
    val orangeContainerColor = Color(0xFFA93700)
    val orangeContentColor = Color(0xFFFFDBCF)
    val greenContainerColor = Color(0xFF6CFF82)
    val greenContentColor = Color(0xFF002106)
    when (this) {
        "blue" -> {
            cardColorModel =
                CardColorModel(contentColor = blueContentColor, containerColor = blueContainerColor)
        }

        "orange" -> {
            cardColorModel = CardColorModel(
                contentColor = orangeContentColor,
                containerColor = orangeContainerColor
            )
        }

        "green" -> {
            cardColorModel = CardColorModel(
                contentColor = greenContentColor,
                containerColor = greenContainerColor
            )
        }

        else -> {
            cardColorModel =
                CardColorModel(contentColor = Color.Black, containerColor = Color.White)
        }
    }

    return cardColorModel
}