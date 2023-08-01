package com.mohasihab.todolistcompose.ui.state

import androidx.compose.ui.focus.FocusState
import java.util.Date

sealed class AddTodoEvent {
    data class InputTitle(val value: String) : AddTodoEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddTodoEvent()
    data class InputDescription(val value: String) : AddTodoEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddTodoEvent()
    data class InputColorLabel(val value: String) : AddTodoEvent()
    data class InputDueDate(val value: Date) : AddTodoEvent()
    data class ChangeColor(val value: String) : AddTodoEvent()

    object SaveTodo : AddTodoEvent()
}
