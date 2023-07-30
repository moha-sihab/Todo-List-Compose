package com.mohasihab.todolistcompose.core.domain.mapper

import androidx.compose.ui.graphics.Color
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import com.mohasihab.todolistcompose.core.domain.model.CardColorModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskDisplayModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import java.util.Date

fun TodoTaskEntity?.toTaskModel(): TodoTaskModel {
    return TodoTaskModel(
        id = this?.id ?: 0,
        title = this?.title ?: "",
        description = this?.description ?: "",
        duedate = this?.duedate ?: Date(),
        colorlabel = this?.colorlabel ?: ""
    )
}

fun List<TodoTaskEntity>.toTaskList(): List<TodoTaskModel> {
    val taskList: MutableList<TodoTaskModel> = mutableListOf()

    this.forEach {
        taskList.add(it.toTaskModel())
    }

    return taskList
}

fun List<TodoTaskEntity>.toMap() = map { it.toTaskDisplay() }

fun TodoTaskModel.toTaskEntity(): TodoTaskEntity {
    return TodoTaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        duedate = this.duedate,
        colorlabel = this.colorlabel
    )
}

fun TodoTaskEntity?.toTaskDisplay(): TodoTaskDisplayModel {
    var cardColorModel =
        CardColorModel(contentColor = Color.Black, containerColor = Color.White)
    val blueContainerColor = Color(0xFF1B60A5)
    val blueContentColor = Color(0xFFD4E3FF)
    val orangeContainerColor = Color(0xFFA93700)
    val orangeContentColor = Color(0xFFFFDBCF)
    val greenContainerColor = Color(0xFF6CFF82)
    val greenContentColor = Color(0xFF002106)

    when (this?.colorlabel) {
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

    return TodoTaskDisplayModel(
        todoTask = this.toTaskModel(),
        cardColorModel = cardColorModel
    )
}

