package com.mohasihab.todolistcompose.ui.screen.today

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mohasihab.todolistcompose.R
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskDisplayModel
import com.mohasihab.todolistcompose.core.domain.model.TodoTaskModel
import com.mohasihab.todolistcompose.core.utils.Converter
import com.mohasihab.todolistcompose.core.utils.DateDisplayFormatter.getDayName
import com.mohasihab.todolistcompose.core.utils.DateDisplayFormatter.getDayOfMonth
import com.mohasihab.todolistcompose.core.utils.DateDisplayFormatter.getMonthName
import com.mohasihab.todolistcompose.core.utils.DateDisplayFormatter.toDefaultDisplay
import com.mohasihab.todolistcompose.ui.component.AppTopBar
import com.mohasihab.todolistcompose.ui.component.EmptyData
import com.mohasihab.todolistcompose.ui.component.ErrorData
import com.mohasihab.todolistcompose.ui.component.SwipeBackground
import com.mohasihab.todolistcompose.ui.navigation.Screen
import com.mohasihab.todolistcompose.ui.state.UiState
import com.mohasihab.todolistcompose.ui.theme.Spacing
import com.mohasihab.todolistcompose.ui.theme.TodoListComposeTheme
import com.mohasihab.todolistcompose.ui.theme.light_GreenContainer
import com.mohasihab.todolistcompose.ui.theme.md_theme_light_secondary
import java.util.Calendar

@Composable
fun TodoListTodayScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TodoListTodayViewModel = hiltViewModel(),
    navigateToUpdate: (String) -> Unit,
) {

    TodoListComposeTheme {
        Scaffold(
            topBar = {
                AppTopBar(
                    titleTopBar = stringResource(R.string.app_bar_title)
                )
            },
            containerColor = MaterialTheme.colorScheme.background,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        Screen.AddTodo.route?.let {
                            navController.navigate(it) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        Screen.AddTodo.route?.let { navController.navigate(it) }
                    },
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.background
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
                }
            },
        ) {
            viewModel.getListTodayUiState()
                .collectAsState(initial = UiState.Loading()).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            viewModel.getTodoListToday()
                        }

                        is UiState.Success -> {
                            TodoListTodayContent(
                                paddingValues = it,
                                todoList = uiState.data ?: mutableListOf(),
                                viewModel = viewModel,
                                navigateToUpdate = navigateToUpdate
                            )

                        }

                        is UiState.Empty -> {
                            EmptyData()
                        }

                        is UiState.Error -> {
                            ErrorData(message = uiState.message.toString())
                        }
                    }
                }
        }
    }

}

@Composable
fun TodoListTodayContent(
    paddingValues: PaddingValues,
    todoList: List<TodoTaskDisplayModel>,
    viewModel: TodoListTodayViewModel,
    navigateToUpdate: (String) -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        state = listState
    ) {
        item {
            TodoListTodayHeader(totalTask = todoList.size)
        }
        items(todoList, key = { it.todoTask.id }) { data ->
            TodoTodaySwipeLayout(
                data = data,
                viewModel::deleteTodoList,
                viewModel::checkDoneTodoList,
                navigateToUpdate
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTodaySwipeLayout(
    data: TodoTaskDisplayModel,
    onRemove: (TodoTaskModel) -> Unit,
    onCheckDOne: (TodoTaskModel) -> Unit,
    navigateToUpdate: (String) -> Unit,
) {
    var show by remember { mutableStateOf(true) }
    var deleteAction by remember { mutableStateOf(false) }
    var checkDoneAction by remember { mutableStateOf(false) }
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToEnd) {
                deleteAction = true
                checkDoneAction = false
                show = false
                true
            } else if (it == DismissValue.DismissedToStart) {
                deleteAction = false
                checkDoneAction = true
                show = false
                true
            } else false
        }, positionalThreshold = { 150.dp.toPx() }
    )
    AnimatedVisibility(
        show, exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                SwipeBackground(dismissState = dismissState)
            },
            dismissContent = {
                TodoListTodayItem(data = data, navigateToUpdate)
            }
        )
    }

    LaunchedEffect(key1 = deleteAction) {
        if (deleteAction) {
            onRemove(data.todoTask)
        }
    }

    LaunchedEffect(key1 = checkDoneAction) {
        if (checkDoneAction) {
            onCheckDOne(data.todoTask.copy(done = true))
        }
    }
}

@Composable
fun TodoListTodayItem(
    data: TodoTaskDisplayModel,
    navigateToUpdate: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToUpdate(data.todoTask.id.toString())
            }
            .heightIn(min = 60.dp)
            .padding(start = Spacing().medium, end = Spacing().medium, top = Spacing().small),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = data.cardColorModel.containerColor,
            contentColor = data.cardColorModel.contentColor
        )
    ) {

        val limitCharacter = 40
        var descriptionWithLimit = ""
        var expand by remember { mutableStateOf(false) }
        var iconExpand: ImageVector by remember { mutableStateOf(Icons.Default.KeyboardArrowDown) }
        descriptionWithLimit = if (data.todoTask.description.length > limitCharacter) {
            data.todoTask.description.substring(0, limitCharacter) + "..."
        } else {
            data.todoTask.description ?: ""
        }

        iconExpand = if (expand) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing().medium),
            horizontalArrangement = Arrangement.spacedBy(Spacing().small),
        ) {
            Text(
                modifier = Modifier
                    .weight(2.5f),
                text = data.todoTask.duedate.toDefaultDisplay(),
                style = MaterialTheme.typography.bodyLarge,
                softWrap = true,
            )

        }

        Text(
            modifier = Modifier
                .padding(start = Spacing().medium, end = Spacing().medium),
            text = data.todoTask.title,
            style = MaterialTheme.typography.headlineMedium,
            softWrap = true,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Spacing().medium,
                    end = Spacing().medium,
                    bottom = Spacing().medium
                )
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            horizontalArrangement = Arrangement.spacedBy(Spacing().small),
        ) {

            Text(
                modifier = Modifier
                    .weight(2.5f)
                    .padding(end = Spacing().medium),
                text = if (expand) data.todoTask.description else descriptionWithLimit,
                style = MaterialTheme.typography.bodyMedium,
                softWrap = true,
            )

            if (data.todoTask.description.length > limitCharacter) {
                Icon(
                    modifier = Modifier
                        .weight(0.2f, fill = true)
                        .clickable {
                            expand = !expand
                        },
                    imageVector = iconExpand,
                    contentDescription = stringResource(R.string.content_description_expand_description)
                )
            }
        }
    }


}


@Composable
fun TodoListTodayHeader(totalTask: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp)
            .padding(start = Spacing().medium, end = Spacing().medium, top = Spacing().small),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = md_theme_light_secondary,
            contentColor = light_GreenContainer
        )
    ) {
        val dayName = Converter.toDate(Calendar.getInstance().timeInMillis)?.getDayName() ?: ""
        val monthName = Converter.toDate(Calendar.getInstance().timeInMillis)?.getMonthName() ?: ""
        val todayDate = Converter.toDate(Calendar.getInstance().timeInMillis)?.getDayOfMonth() ?: ""

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(Spacing().medium),
            horizontalArrangement = Arrangement.spacedBy(Spacing().small),
        ) {
            Column(
                modifier = Modifier.weight(0.3f),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = dayName,
                    style = MaterialTheme.typography.bodyMedium,
                    softWrap = true,
                )
                Text(
                    text = todayDate,
                    style = MaterialTheme.typography.displayMedium,
                    softWrap = true,
                )
                Text(
                    text = monthName,
                    style = MaterialTheme.typography.headlineLarge,
                    softWrap = true,
                )
            }

            Divider(
                color = light_GreenContainer,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(
                modifier = Modifier.weight(0.7f),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(R.string.you_have),
                    style = MaterialTheme.typography.headlineMedium,
                    softWrap = true,
                )
                Text(
                    text = stringResource(R.string.total_task, totalTask),
                    style = MaterialTheme.typography.headlineLarge,
                    softWrap = true,
                )
            }
        }
    }
}
