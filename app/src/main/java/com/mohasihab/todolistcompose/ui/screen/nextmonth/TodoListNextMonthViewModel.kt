package com.mohasihab.todolistcompose.ui.screen.nextmonth

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
class TodoListNextMonthViewModel @Inject constructor(private val useCase: TodoTaskUseCaseContract) :
    ViewModel() {
    private var todoListNextMonthUiState: MutableStateFlow<UiState<List<TodoTaskDisplayModel>>> =
        MutableStateFlow(UiState.Loading())

    fun getListNextMonthUiState(): StateFlow<UiState<List<TodoTaskDisplayModel>>> {
        return todoListNextMonthUiState
    }

    fun setListNextMonthUIState(state: UiState<List<TodoTaskDisplayModel>>) {
        todoListNextMonthUiState.value = state
    }

    fun getTodoListNextMonth() {
        viewModelScope.launch {
            useCase.getTaskNextMonth().collectLatest { result ->
                when (result) {
                    is ResultState.Success -> {
                        result.data.let {
                            if (it != null) {
                                setListNextMonthUIState(UiState.Success(it))
                            } else {
                                setListNextMonthUIState(UiState.Empty())
                            }
                        }
                    }

                    is ResultState.Error -> {
                        todoListNextMonthUiState.value = UiState.Error(result.message.toString())
                    }

                    else -> {
                        todoListNextMonthUiState.value = UiState.Loading()
                    }
                }
            }
        }
    }
}