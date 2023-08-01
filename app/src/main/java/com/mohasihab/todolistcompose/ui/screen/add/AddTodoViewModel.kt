package com.mohasihab.todolistcompose.ui.screen.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import com.mohasihab.todolistcompose.core.domain.usecase.TodoTaskUseCaseContract
import com.mohasihab.todolistcompose.core.utils.DateDisplayFormatter.toDefaultDisplay
import com.mohasihab.todolistcompose.ui.state.AddTodoEvent
import com.mohasihab.todolistcompose.ui.state.AddUpdateTodoState
import com.mohasihab.todolistcompose.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddTodoViewModel @Inject constructor(private val useCase: TodoTaskUseCaseContract) :
    ViewModel() {
    var addUpdateTodoState by mutableStateOf(AddUpdateTodoState())
    private var addTodoUiState: MutableStateFlow<UiState<String>> =
        MutableStateFlow(UiState.Loading())

    fun onEvent(event: AddTodoEvent) {
        when (event) {
            is AddTodoEvent.InputTitle -> {
                addUpdateTodoState = addUpdateTodoState.copy(title = event.value)
            }

            is AddTodoEvent.ChangeTitleFocus -> {
                addUpdateTodoState =
                    addUpdateTodoState.copy(hintTitleVisibility = !event.focusState.isFocused && addUpdateTodoState.title.isBlank())
            }

            is AddTodoEvent.InputDescription -> {
                addUpdateTodoState = addUpdateTodoState.copy(description = event.value)
            }

            is AddTodoEvent.ChangeDescriptionFocus -> {
                addUpdateTodoState =
                    addUpdateTodoState.copy(hintDescriptionVisibility = !event.focusState.isFocused && addUpdateTodoState.description.isBlank())
            }

            is AddTodoEvent.InputColorLabel -> {
                addUpdateTodoState = addUpdateTodoState.copy(colorLabel = event.value)
            }

            is AddTodoEvent.InputDueDate -> {
                addUpdateTodoState = addUpdateTodoState.copy(
                    dueDate = event.value,
                    dueDateDisplay = event.value.toDefaultDisplay()
                )
            }


            is AddTodoEvent.SaveTodo -> {

                viewModelScope.launch {
                    try {
                        val todo = TodoTaskModel(
                            id = 0,
                            title = addUpdateTodoState.title,
                            description = addUpdateTodoState.description,
                            duedate = addUpdateTodoState.dueDate,
                            colorlabel = addUpdateTodoState.colorLabel,
                            done = false
                        )
                        useCase.addTask(todo)
                        addTodoUiState.emit(UiState.Success(""))
                        addUpdateTodoState = addUpdateTodoState.copy(
                            uiState = addTodoUiState.value,
                            actionStatus = true
                        )
                    } catch (e: Exception) {
                        addTodoUiState.emit(UiState.Error(message = e.message.toString()))
                        addUpdateTodoState = addUpdateTodoState.copy(
                            uiState = addTodoUiState.value,
                            actionStatus = false
                        )
                    }

                }


            }

            else -> {}
        }
    }
}