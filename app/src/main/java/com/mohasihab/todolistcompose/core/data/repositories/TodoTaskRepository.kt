package com.mohasihab.todolistcompose.core.data.repositories

import com.mohasihab.todolistcompose.core.data.local.TodoTaskDao
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import javax.inject.Inject

class TodoTaskRepository @Inject constructor(private val dao: TodoTaskDao) :
    TodoTaskRepositoryContract {
    override suspend fun addTask(todoTaskEntity: TodoTaskEntity) = dao.addTask(todoTaskEntity)

    override suspend fun updateTask(todoTaskEntity: TodoTaskEntity) = dao.updateTask(todoTaskEntity)

    override suspend fun deleteTask(todoTaskEntity: TodoTaskEntity) = dao.deleteTask(todoTaskEntity)

    override suspend fun getAllTask(): List<TodoTaskEntity> = dao.getAllTask()

    override suspend fun getTaskToday(): List<TodoTaskEntity> = dao.getTaskToday()

    override suspend fun getTaskNextMonth(): List<TodoTaskEntity> = dao.getTaskNextMonth()
    override suspend fun getTaskById(id: Int): TodoTaskEntity = dao.getTaskById(id = id)
}