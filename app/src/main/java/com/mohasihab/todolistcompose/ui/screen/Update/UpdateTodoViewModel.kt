package com.mohasihab.todolistcompose.ui.screen.Update

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import com.mohasihab.todolistcompose.core.domain.usecase.TodoTaskUseCaseContract
import com.mohasihab.todolistcompose.core.utils.DateDisplayFormatter.toDefaultDisplay
import com.mohasihab.todolistcompose.core.utils.ResultState
import com.mohasihab.todolistcompose.ui.state.AddTodoEvent
import com.mohasihab.todolistcompose.ui.state.AddUpdateTodoState
import com.mohasihab.todolistcompose.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class UpdateTodoViewModel @Inject constructor(private val useCase: TodoTaskUseCaseContract) :
    ViewModel() {
    var updateDateTodoState by mutableStateOf(AddUpdateTodoState())
    private var updateTodoUiState: MutableStateFlow<UiState<String>> =
        MutableStateFlow(UiState.Loading())

    fun getTaskById(id: String) {
        viewModelScope.launch {
            useCase.getTaskById(id.toInt()).collectLatest { result ->
                when (result) {
                    is ResultState.Success -> {
                        result.data.let {
                            onEvent(AddTodoEvent.InputTitle(it?.todoTask?.title ?: ""))
                            onEvent(AddTodoEvent.InputDueDate(it?.todoTask?.duedate ?: Date()))
                            onEvent(AddTodoEvent.InputDescription(it?.todoTask?.description ?: ""))
                            onEvent(AddTodoEvent.InputColorLabel(it?.todoTask?.colorlabel ?: ""))
                            onEvent(AddTodoEvent.InputId(it?.todoTask?.id ?: 0))
                            updateDateTodoState.copy(hintTitleVisibility = false)
                            updateDateTodoState.copy(hintDescriptionVisibility = false)
                        }
                    }

                    is ResultState.Error -> {

                    }

                    else -> {

                    }

                }
            }
        }
    }

    fun onEvent(event: AddTodoEvent) {
        when (event) {
            is AddTodoEvent.InputTitle -> {
                updateDateTodoState = updateDateTodoState.copy(title = event.value)
            }

            is AddTodoEvent.ChangeTitleFocus -> {
                updateDateTodoState =
                    updateDateTodoState.copy(hintTitleVisibility = !event.focusState.isFocused && updateDateTodoState.title.isBlank())
            }

            is AddTodoEvent.InputDescription -> {
                updateDateTodoState = updateDateTodoState.copy(description = event.value)
            }

            is AddTodoEvent.ChangeDescriptionFocus -> {
                updateDateTodoState =
                    updateDateTodoState.copy(hintDescriptionVisibility = !event.focusState.isFocused && updateDateTodoState.description.isBlank())
            }

            is AddTodoEvent.InputColorLabel -> {
                updateDateTodoState = updateDateTodoState.copy(colorLabel = event.value)
            }

            is AddTodoEvent.InputDueDate -> {
                updateDateTodoState = updateDateTodoState.copy(
                    dueDate = event.value,
                    dueDateDisplay = event.value.toDefaultDisplay()
                )
            }

            is AddTodoEvent.InputId -> {
                updateDateTodoState = updateDateTodoState.copy(
                    id = event.value
                )
            }


            is AddTodoEvent.SaveTodo -> {

                viewModelScope.launch {
                    try {
                        val todo = TodoTaskModel(
                            id = updateDateTodoState.id,
                            title = updateDateTodoState.title,
                            description = updateDateTodoState.description,
                            duedate = updateDateTodoState.dueDate,
                            colorlabel = updateDateTodoState.colorLabel,
                            done = false
                        )
                        useCase.updateTask(todo)
                        updateTodoUiState.emit(UiState.Success(""))
                        updateDateTodoState = updateDateTodoState.copy(
                            uiState = updateTodoUiState,
                            actionStatus = true
                        )
                    } catch (e: Exception) {
                        updateTodoUiState.emit(UiState.Error(message = e.message.toString()))
                        updateDateTodoState = updateDateTodoState.copy(
                            uiState = updateTodoUiState,
                            actionStatus = false
                        )
                    }

                }


            }

            else -> {}
        }
    }
}