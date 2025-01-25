package com.example.todoapplication.repository

import com.example.todoapplication.model.todo.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    // 初期値としてasLiveData()でLiveDataにいれておく
    fun getAll(): Flow<List<ToDo>>
    suspend fun create(title: String, detail: String)

    // 更新、削除処理は時間がかかる処理のため、メソッドにsuspendをつける
    suspend fun update(todo: ToDo, title: String, detail: String): ToDo
    suspend fun delete(todo: ToDo)
}