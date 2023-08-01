package com.mohasihab.todolistcompose.ui.state

import java.util.Date

data class AddUpdateTodoState(
    val title : String = "",
    val description : String = "",
    val dueDate : Date = Date(),
    val dueDateDisplay : String = "",
    val colorLabel : String = "",
    val uiState : UiState<String> = UiState.Empty(),
    val actionStatus : Boolean = false,
    val hintTitleVisibility : Boolean = true,
    val hintDescriptionVisibility : Boolean = true,
)
