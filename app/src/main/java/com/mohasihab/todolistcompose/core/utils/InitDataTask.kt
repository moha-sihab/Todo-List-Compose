package com.mohasihab.todolistcompose.core.utils

import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import java.util.Calendar
import java.util.Date

object InitDataTask {
    fun getTask(): List<TodoTaskEntity> {
        val calendar = Calendar.getInstance()
        val description =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam euismod mi nunc, eu dignissim risus vehicula non. Quisque eu ultrices nisl, a blandit eros. Sed in est id neque blandit laoreet eu luctus diam. Aliquam blandit, sem a ultrices sodales, risus metus euismod lacus, sed venenatis ipsum elit in leo. Donec ut ligula condimentum, tincidunt mauris at, consequat tortor"
        val today = Converter.toDate(Calendar.getInstance().timeInMillis)!!
        val nextMonth = getNextMonth(calendar.time)!!

        return listOf(
            TodoTaskEntity(
                id = 1,
                title = "You have meeting with client A",
                description = description,
                duedate = nextMonth,
                colorlabel = "blue",
                done = false,
            ),
            TodoTaskEntity(
                id = 2,
                title = "You have meeting with client 2",
                description = description,
                duedate = today,
                colorlabel = "orange",
                done = false,
            ),
            TodoTaskEntity(
                id = 3,
                title = "You have meeting with client C",
                description = description,
                duedate = today,
                colorlabel = "green",
                done = false,
            ),
            TodoTaskEntity(
                id = 4,
                title = "You have meeting with client D",
                description = description,
                duedate = today,
                colorlabel = "blue",
                done = false,
            ),
            TodoTaskEntity(
                id = 5,
                title = "You have meeting with client E",
                description = description,
                duedate = nextMonth,
                colorlabel = "green",
                done = false,
            ),
            TodoTaskEntity(
                id = 6,
                title = "You have meeting with client F",
                description = description,
                duedate = today,
                colorlabel = "blue",
                done = false,
            ),
            TodoTaskEntity(
                id = 7,
                title = "Watching Juventus vs AC Milan",
                description = description,
                duedate = nextMonth,
                colorlabel = "green",
                done = false,
            ),
            TodoTaskEntity(
                id = 8,
                title = "You have meeting with client x",
                description = description,
                duedate = nextMonth,
                colorlabel = "blue",
                done = false,
            ),
            TodoTaskEntity(
                id = 9,
                title = "Interview with C Level",
                description = description,
                duedate = today,
                colorlabel = "orange",
                done = false,
            ),
            TodoTaskEntity(
                id = 10,
                title = "You have meeting with client Z",
                description = description,
                duedate = today,
                colorlabel = "blue",
                done = false,
            ),
        )
    }

    fun getNextMonth(date: Date?): Date? {
        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }
        if (calendar[Calendar.MONTH] == Calendar.DECEMBER) {
            calendar[Calendar.MONTH] = Calendar.JANUARY
            calendar[Calendar.YEAR] = calendar[Calendar.YEAR] + 1
        } else {
            calendar.roll(Calendar.MONTH, true)
        }
        return calendar.time
    }
}