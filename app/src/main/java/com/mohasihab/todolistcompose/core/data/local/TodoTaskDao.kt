package com.mohasihab.todolistcompose.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mohasihab.todolistcompose.core.data.local.entity.TodoTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(todoTaskEntity: TodoTaskEntity)

    @Update
    suspend fun updateTask(todoTaskEntity: TodoTaskEntity)

    @Delete
    suspend fun deleteTask(todoTaskEntity: TodoTaskEntity)

    @Query("select * from table_todo")
    fun getAllTask(): Flow<List<TodoTaskEntity>>


}