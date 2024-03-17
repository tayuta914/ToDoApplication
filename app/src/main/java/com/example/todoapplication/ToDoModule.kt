package com.example.todoapplication

import android.content.Context
import androidx.room.Room
import com.example.todoapplication.model.todo.ToDoDAO
import com.example.todoapplication.model.todo.ToDoDatabase
import com.example.todoapplication.repository.ToDoRepository
import com.example.todoapplication.repository.ToDoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToDoModule {

    @Singleton
    @Provides
    fun provideToDoDatabase(
        @ApplicationContext context: Context
    ): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            "todo.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideToDoDao(db: ToDoDatabase): ToDoDAO {
        return db.todoDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ToDoRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        impl: ToDoRepositoryImpl
    ): ToDoRepository
}