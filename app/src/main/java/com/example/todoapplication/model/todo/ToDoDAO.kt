package com.example.todoapplication.model.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {

    @Query("select * from ToDo where created < :startCreated order by created desc LIMIT :limit")
    fun getWithCreated(startCreated: Long, limit: Int): Flow<List<ToDo>>

    @Query("select * from ToDo order by created desc")
    fun getAll(): Flow<List<ToDo>>

    @Insert
    suspend fun create(toDo: ToDo): Long

    @Update
    suspend fun update(toDo: ToDo): Int

    @Delete
    suspend fun delete(toDo: ToDo): Int
}