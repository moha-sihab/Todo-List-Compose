package com.mohasihab.todolistcompose.core.data.repositories

import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import kotlinx.coroutines.flow.Flow

interface TodoTaskRepositoryContract {
    suspend fun addTask(todoTaskEntity: TodoTaskEntity)

    suspend fun updateTask(todoTaskEntity: TodoTaskEntity)

    suspend fun deleteTask(todoTaskEntity: TodoTaskEntity)

    suspend fun getAllTask(): List<TodoTaskEntity>
}