package com.mohasihab.todolistcompose.core.domain.mapper

import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel

fun TodoTaskEntity.toTaskModel(): TodoTaskModel {
    return TodoTaskModel(
        id = this.id,
        title = this.title,
        description = this.description,
        duedate = this.duedate,
        colorlabel = this.colorlabel
    )
}

fun List<TodoTaskEntity>.toTaskList(): List<TodoTaskModel> {
    val taskList: MutableList<TodoTaskModel> = mutableListOf()

    this.forEach {
        taskList.add(it.toTaskModel())
    }

    return taskList
}
fun List<TodoTaskEntity>.toMap() = map {it.toTaskModel()}

fun TodoTaskModel.toTaskEntity(): TodoTaskEntity {
    return TodoTaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        duedate = this.duedate,
        colorlabel = this.colorlabel
    )
}

