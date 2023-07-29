package com.mohasihab.todolistcompose.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity

@Database(entities = [TodoTaskEntity::class], version = 1, exportSchema = false)
abstract class TodoTaskDatabase : RoomDatabase() {
    abstract fun todoTaskDao(): TodoTaskDao
}