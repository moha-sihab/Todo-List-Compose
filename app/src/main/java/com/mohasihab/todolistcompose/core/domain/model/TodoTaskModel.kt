package com.mohasihab.todolistcompose.core.domain.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoTaskModel(
    val id: Int,
    val title: String,
    val description: String,
    val duedate: Date,
    val colorlabel: String,
) : Parcelable


data class TodoTaskDisplayModel(
    val todoTask : TodoTaskModel,
    val cardColorModel: CardColorModel,
)


data class CardColorModel(
    val containerColor : Color,
    val contentColor : Color,
)

