package com.mohasihab.todolistcompose.ui.navigation

sealed class Screen(val route: String) {
    object TaskToday : Screen("tasktoday")
    object TaskNextMonth : Screen("tasknextmonth")
    object AddTodo : Screen("addtodo")
}