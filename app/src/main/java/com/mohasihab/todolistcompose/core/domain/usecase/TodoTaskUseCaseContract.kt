package com.mohasihab.todolistcompose.core.domain.usecase

import com.mohasihab.todolistcompose.core.domain.model.TodoTaskDisplayModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import com.mohasihab.todolistcompose.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface TodoTaskUseCaseContract {
    suspend fun addTask(todoTask: TodoTaskModel)

    suspend fun updateTask(todoTask: TodoTaskModel)

    suspend fun deleteTask(todoTask: TodoTaskModel)

    fun getAllTask(): Flow<ResultState<List<TodoTaskDisplayModel>>>

    fun getTaskToday() : Flow<ResultState<List<TodoTaskDisplayModel>>>

    fun getTaskNextMonth() : Flow<ResultState<List<TodoTaskDisplayModel>>>

    fun getTaskById(id : Int) : Flow<ResultState<TodoTaskDisplayModel>>
}