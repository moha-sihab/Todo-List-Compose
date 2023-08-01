package com.mohasihab.todolistcompose.core.domain.mapper

import androidx.compose.ui.graphics.Color
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import com.mohasihab.todolistcompose.core.domain.model.CardColorModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskDisplayModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import com.mohasihab.todolistcompose.core.utils.toTaskColor
import java.util.Date

fun TodoTaskEntity?.toTaskModel(): TodoTaskModel {
    return TodoTaskModel(
        id = this?.id ?: 0,
        title = this?.title ?: "",
        description = this?.description ?: "",
        duedate = this?.duedate ?: Date(),
        colorlabel = this?.colorlabel ?: "",
        done = this?.done ?: false
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
        colorlabel = this.colorlabel,
        done = this.done
    )
}

fun TodoTaskEntity?.toTaskDisplay(): TodoTaskDisplayModel {
    var cardColorModel = this?.colorlabel?.toTaskColor() ?: "".toTaskColor()

    return TodoTaskDisplayModel(
        todoTask = this.toTaskModel(),
        cardColorModel = cardColorModel
    )
}

