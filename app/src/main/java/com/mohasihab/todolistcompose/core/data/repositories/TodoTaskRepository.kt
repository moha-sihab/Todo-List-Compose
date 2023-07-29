package com.mohasihab.todolistcompose.core.data.repositories

import com.mohasihab.todolistcompose.core.data.local.TodoTaskDao
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TodoTaskRepository @Inject constructor(private val dao: TodoTaskDao) :
    TodoTaskRepositoryContract {
    override suspend fun addTask(todoTaskEntity: TodoTaskEntity) = dao.addTask(todoTaskEntity)

    override suspend fun updateTask(todoTaskEntity: TodoTaskEntity) = dao.updateTask(todoTaskEntity)

    override suspend fun deleteTask(todoTaskEntity: TodoTaskEntity) = dao.deleteTask(todoTaskEntity)

    override fun getAllTask(): Flow<List<TodoTaskEntity>> = dao.getAllTask()
}