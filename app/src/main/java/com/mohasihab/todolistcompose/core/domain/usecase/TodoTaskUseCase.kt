package com.mohasihab.todolistcompose.core.domain.usecase

import com.mohasihab.todolistcompose.core.data.repositories.TodoTaskRepositoryContract
import com.mohasihab.todolistcompose.core.domain.mapper.responseErrorToResultStateError
import com.mohasihab.todolistcompose.core.domain.mapper.toMap
import com.mohasihab.todolistcompose.core.domain.mapper.toTaskDisplay
import com.mohasihab.todolistcompose.core.domain.mapper.toTaskEntity
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskDisplayModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import com.mohasihab.todolistcompose.core.utils.ResultState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoTaskUseCase @Inject constructor(private val repository: TodoTaskRepositoryContract) :
    TodoTaskUseCaseContract {
    override suspend fun addTask(todoTask: TodoTaskModel) {
        repository.addTask(todoTask.toTaskEntity())
    }

    override suspend fun updateTask(todoTask: TodoTaskModel) {
        repository.updateTask(todoTask.toTaskEntity())
    }

    override suspend fun deleteTask(todoTask: TodoTaskModel) {
        repository.deleteTask(todoTask.toTaskEntity())
    }

    override fun getAllTask(): Flow<ResultState<List<TodoTaskDisplayModel>>> =
        flow {
            try {
                emit(ResultState.Loading())
                val result = repository.getAllTask()
                emit(ResultState.Success(result.toMap()))

            } catch (e: Throwable) {
                emit(responseErrorToResultStateError(e))
            }
        }

    override fun getTaskToday(): Flow<ResultState<List<TodoTaskDisplayModel>>> =
        flow {
            try {
                emit(ResultState.Loading())
                val result = repository.getTaskToday()
                emit(ResultState.Success(result.toMap()))

            } catch (e: Throwable) {
                emit(responseErrorToResultStateError(e))
            }
        }


    override fun getTaskNextMonth(): Flow<ResultState<List<TodoTaskDisplayModel>>> =
        flow {
            try {
                emit(ResultState.Loading())
                val result = repository.getTaskNextMonth()
                emit(ResultState.Success(result.toMap()))

            } catch (e: Throwable) {
                emit(responseErrorToResultStateError(e))
            }
        }

    override fun getTaskById(id: Int): Flow<ResultState<TodoTaskDisplayModel>> =
        flow {
            try {
                emit(ResultState.Loading())
                val result = repository.getTaskById(id)
                emit(ResultState.Success(result.toTaskDisplay()))
            } catch (e: Throwable) {
                emit(responseErrorToResultStateError(e))
            }
        }

}