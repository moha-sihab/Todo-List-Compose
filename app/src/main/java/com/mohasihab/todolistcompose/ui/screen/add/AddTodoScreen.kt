package com.mohasihab.todolistcompose.ui.screen.add

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.mohasihab.todolistcompose.R
import com.mohasihab.todolistcompose.core.utils.toTaskColor
import com.mohasihab.todolistcompose.ui.component.AppTopBar
import com.mohasihab.todolistcompose.ui.component.ColorPicker
import com.mohasihab.todolistcompose.ui.component.DueDate
import com.mohasihab.todolistcompose.ui.component.TextInput
import com.mohasihab.todolistcompose.ui.state.AddTodoEvent
import com.mohasihab.todolistcompose.ui.theme.Spacing
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import kotlinx.coroutines.launch


@Composable
fun AddTodoScreen(
    navController: NavController,
    viewmodel: AddTodoViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            AppTopBar(
                titleTopBar = stringResource(R.string.add_task),
                addTodo = true,
                onCloseClick = { navController.navigateUp() },
                onSaveClick = { viewmodel.onEvent(AddTodoEvent.SaveTodo) })
        }
    ) { it ->
        AddTodoContent(paddingValues = it, viewmodel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoContent(paddingValues: PaddingValues, viewmodel: AddTodoViewModel) {
    val colorLabel = viewmodel.addUpdateTodoState.colorLabel
    val containerAnimatable = remember {
        Animatable(
            colorLabel.toTaskColor().containerColor
        )
    }
    val contentAnimatable = remember {
        Animatable(
            colorLabel.toTaskColor().contentColor
        )
    }

    val scope = rememberCoroutineScope()


    //date picker
    val selectedDate = remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    val calendarState = rememberUseCaseState(false)
    val defaultZoneId = ZoneId.systemDefault()
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.WEEK,
        ),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate.value
        ) { newDate ->
            selectedDate.value = newDate
            //    val newDateLong = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            val newDateLong: Date = Date.from(newDate.atStartOfDay(defaultZoneId).toInstant())
            viewmodel.onEvent(AddTodoEvent.InputDueDate(newDateLong))
        },
    )
    //


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerAnimatable.value)
            .padding(paddingValues)
            .padding(Spacing().large),
        verticalArrangement = Arrangement.spacedBy(Spacing().small),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing().small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DueDate(
                modifier = Modifier.weight(0.5f),
                dateDisplay = viewmodel.addUpdateTodoState.dueDateDisplay,
                contentColor = contentAnimatable.value,
                onClick = {
                    calendarState.show()
                }
            )
            ColorPicker(modifier = Modifier.weight(0.5f), onClick = {
                viewmodel.onEvent(AddTodoEvent.InputColorLabel(it))
                scope.launch {
                    containerAnimatable.animateTo(
                        targetValue = colorLabel.toTaskColor().containerColor,
                        animationSpec = tween(
                            durationMillis = 500
                        )
                    )
                    contentAnimatable.animateTo(
                        targetValue = colorLabel.toTaskColor().contentColor,
                        animationSpec = tween(
                            durationMillis = 500
                        )
                    )
                }
            })
        }

        TextInput(
            text = viewmodel.addUpdateTodoState.title,
            hint = stringResource(R.string.hint_title),
            onFocusChange = {
                viewmodel.onEvent(AddTodoEvent.ChangeTitleFocus(it))
            },
            onValueChange = {
                viewmodel.onEvent(AddTodoEvent.InputTitle(it))
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.titleMedium.copy(color = contentAnimatable.value),
            isHintVisible = viewmodel.addUpdateTodoState.hintTitleVisibility
        )

        TextInput(
            text = viewmodel.addUpdateTodoState.description,
            modifier = Modifier.fillMaxHeight(),
            hint = stringResource(R.string.inbound_description),
            onFocusChange = {
                viewmodel.onEvent(AddTodoEvent.ChangeDescriptionFocus(it))
            },
            onValueChange = {
                viewmodel.onEvent(AddTodoEvent.InputDescription(it))
            },
            textStyle = MaterialTheme.typography.bodySmall.copy(color = contentAnimatable.value),
            isHintVisible = viewmodel.addUpdateTodoState.hintDescriptionVisibility
        )


    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddTodoContentPreview() {
    /*  MaterialTheme {
          Surface {
              AddTodoScreen()
          }
      }*/
}
