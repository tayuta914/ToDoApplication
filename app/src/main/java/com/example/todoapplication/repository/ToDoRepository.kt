package com.example.todoapplication.repository

import com.example.todoapplication.model.todo.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun create(title: String, detail: String)
    // 初期値としてasLiveData()でLiveDataにいれておく
    fun getAll(): Flow<List<ToDo>>
}