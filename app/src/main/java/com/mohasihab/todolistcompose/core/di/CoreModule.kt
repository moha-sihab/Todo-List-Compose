package com.mohasihab.todolistcompose.core.di

import android.content.Context
import androidx.room.Room
import com.mohasihab.todolistcompose.core.data.local.RoomDbInitializer
import com.mohasihab.todolistcompose.core.data.local.TodoTaskDao
import com.mohasihab.todolistcompose.core.data.local.TodoTaskDatabase
import com.mohasihab.todolistcompose.core.data.repositories.TodoTaskRepository
import com.mohasihab.todolistcompose.core.data.repositories.TodoTaskRepositoryContract
import com.mohasihab.todolistcompose.core.domain.usecase.TodoTaskUseCase
import com.mohasihab.todolistcompose.core.domain.usecase.TodoTaskUseCaseContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        todoProvider: Provider<TodoTaskDao>,
    ) = Room.databaseBuilder(
        context,
        TodoTaskDatabase::class.java,
        "tododatabase"
    ).addCallback(
        RoomDbInitializer(todoProvider = todoProvider)
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: TodoTaskDatabase) = database.todoTaskDao()

    @Singleton
    @Provides
    fun provideRepository(dao: TodoTaskDao): TodoTaskRepositoryContract = TodoTaskRepository(dao)

    @Singleton
    @Provides
    fun provideUseCase(repository: TodoTaskRepositoryContract): TodoTaskUseCaseContract =
        TodoTaskUseCase(repository)
}