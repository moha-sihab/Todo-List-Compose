package com.mohasihab.todolistcompose.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskDisplayModel
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
class TodoListHistoryViewModel @Inject constructor(private val useCase: TodoTaskUseCaseContract) :
    ViewModel() {
    private var todoListHistoryUiState: MutableStateFlow<UiState<List<TodoTaskDisplayModel>>> =
        MutableStateFlow(UiState.Loading())

    fun getListHistoryUiState(): StateFlow<UiState<List<TodoTaskDisplayModel>>> {
        return todoListHistoryUiState
    }

    fun setListHistoryUIState(state: UiState<List<TodoTaskDisplayModel>>) {
        todoListHistoryUiState.value = state
    }

    fun getTodoListHistory() {
        viewModelScope.launch {
            useCase.getAllTask().collectLatest { result ->
                when (result) {
                    is ResultState.Success -> {
                        result.data.let {
                            if (it != null) {
                                setListHistoryUIState(UiState.Success(it))
                            } else {
                                setListHistoryUIState(UiState.Empty())
                            }
                        }
                    }

                    is ResultState.Error -> {
                        todoListHistoryUiState.value = UiState.Error(result.message.toString())
                    }

                    else -> {
                        todoListHistoryUiState.value = UiState.Loading()
                    }
                }
            }
        }
    }
}