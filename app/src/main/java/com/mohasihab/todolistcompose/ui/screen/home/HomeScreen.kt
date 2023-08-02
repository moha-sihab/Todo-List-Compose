package com.mohasihab.todolistcompose.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohasihab.todolistcompose.R
import com.mohasihab.todolistcompose.ui.navigation.NavigationItem
import com.mohasihab.todolistcompose.ui.navigation.Screen
import com.mohasihab.todolistcompose.ui.screen.add.AddTodoScreen
import com.mohasihab.todolistcompose.ui.screen.nextmonth.TodoListNextMonthScreen
import com.mohasihab.todolistcompose.ui.screen.today.TodoListTodayScreen
import com.mohasihab.todolistcompose.ui.theme.TodoListComposeTheme
import com.mohasihab.todolistcompose.ui.theme.spacing

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    TodoListComposeTheme {
        Scaffold(
            bottomBar = {
                if (currentRoute != Screen.AddTodo.route) {
                    BottomBar(navController)
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            modifier = modifier
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.TaskToday.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.TaskToday.route) {
                    TodoListTodayScreen(modifier = Modifier.padding(innerPadding), navController)
                }
                composable(Screen.TaskNextMonth.route) {
                    TodoListNextMonthScreen(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                    )
                }
                composable(Screen.AddTodo.route) {
                    AddTodoScreen(navController = navController)
                }
            }
        }
    }

}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_today),
                icon = Icons.Default.Home,
                screen = Screen.TaskToday
            ),
            NavigationItem(
                title = stringResource(R.string.menu_nextmonth),
                icon = Icons.Default.KeyboardArrowRight,
                screen = Screen.TaskNextMonth
            ),
        )
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {

            navigationItems.map { item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.background,
                        selectedIconColor = MaterialTheme.colorScheme.onBackground
                    ),
                    icon = {
                        Icon(
                            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small),
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            modifier.padding(top = MaterialTheme.spacing.small),
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    TodoListComposeTheme {
        HomeScreen()
    }
}
