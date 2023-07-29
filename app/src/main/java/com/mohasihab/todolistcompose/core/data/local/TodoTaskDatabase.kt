package com.mohasihab.todolistcompose.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import com.mohasihab.todolistcompose.core.utils.Converter

@Database(
    entities = [TodoTaskEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class TodoTaskDatabase : RoomDatabase() {
    abstract fun todoTaskDao(): TodoTaskDao
}