package com.mohasihab.todolistcompose.core.domain.model

import android.os.Parcelable
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoTaskModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val duedate: Date,
    val colorlabel: String,
) : Parcelable
