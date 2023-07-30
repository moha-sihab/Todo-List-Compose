package com.mohasihab.todolistcompose.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity

@Dao
interface TodoTaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(todoTaskEntity: TodoTaskEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun populateTask(todoTaskEntity: List<TodoTaskEntity>)

    @Update
    suspend fun updateTask(todoTaskEntity: TodoTaskEntity)

    @Delete
    suspend fun deleteTask(todoTaskEntity: TodoTaskEntity)

    @Query("select * from table_todo")
    suspend fun getAllTask(): List<TodoTaskEntity>

    @Query("select * from table_todo where strftime('%Y-%m-%d', datetime(duedate/1000, 'unixepoch','localtime'))=date('now','localtime')")
    suspend fun getTaskToday(): List<TodoTaskEntity>

    @Query("select  *  from table_todo where  strftime('%m', datetime(duedate/1000, 'unixepoch','localtime')) =  strftime('%m',datetime('now', 'localtime', '+1 month'))")
    suspend fun getTaskNextMonth(): List<TodoTaskEntity>

}