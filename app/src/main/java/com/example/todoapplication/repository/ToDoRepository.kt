package com.example.todoapplication.repository

interface ToDoRepository {
    suspend fun create(title: String, detail: String)
}