package com.mohasihab.todolistcompose.ui.navigation

import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel

sealed class Screen(val route: String) {
    object TaskToday : Screen("tasktoday")
    object TaskNextMonth : Screen("tasknextmonth")
    object AddTodo : Screen("addtodo")
    object History : Screen("history")
    object UpdateTodo : Screen("taskToday/{id}") {
        fun createRoute(id : String) = "taskToday/$id"
    }
}