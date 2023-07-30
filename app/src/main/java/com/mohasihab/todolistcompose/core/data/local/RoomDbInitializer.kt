package com.mohasihab.todolistcompose.core.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mohasihab.todolistcompose.core.utils.InitDataTask
import javax.inject.Provider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RoomDbInitializer(
    private val todoProvider: Provider<TodoTaskDao>,
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        populateTodoTask()
    }

    private suspend fun populateTodoTask() {
        todoProvider.get().populateTask(InitDataTask.getTask())
    }
}