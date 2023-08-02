package com.mohasihab.todolistcompose.ui.screen.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskDisplayModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import com.mohasihab.todolistcompose.core.domain.usecase.TodoTaskUseCaseContract
import com.mohasihab.todolistcompose.core.utils.ResultState
import com.mohasihab.todolistcompose.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class TodoListTodayViewModel @Inject constructor(private val useCase: TodoTaskUseCaseContract) :
    ViewModel() {
    private var todoListTodayUiState: MutableStateFlow<UiState<List<TodoTaskDisplayModel>>> =
        MutableStateFlow(UiState.Loading())

    fun getListTodayUiState(): StateFlow<UiState<List<TodoTaskDisplayModel>>> {
        return todoListTodayUiState
    }

    fun setListTodayUIState(state: UiState<List<TodoTaskDisplayModel>>) {
        todoListTodayUiState.value = state
    }

    fun getTodoListToday() {
        viewModelScope.launch {
            useCase.getTaskToday().collectLatest { result ->
                when (result) {
                    is ResultState.Success -> {
                        result.data.let {
                            if (it != null) {
                                setListTodayUIState(UiState.Success(it))
                            } else {
                                setListTodayUIState(UiState.Empty())
                            }
                        }
                    }

                    is ResultState.Error -> {
                        todoListTodayUiState.value = UiState.Error(result.message.toString())
                    }

                    else -> {
                        todoListTodayUiState.value = UiState.Loading()
                    }
                }
            }
        }
    }

    fun deleteTodoList(data: TodoTaskModel) {
        viewModelScope.launch {
            useCase.deleteTask(data)
        }
    }

    fun checkDoneTodoList(data: TodoTaskModel) {
        viewModelScope.launch {
            useCase.updateTask(data)
        }
    }
}