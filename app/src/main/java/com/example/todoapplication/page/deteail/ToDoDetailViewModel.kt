package com.example.todoapplication.page.deteail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todoapplication.model.todo.ToDo

class ToDoDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // 中断、再開時に状態の保存と復元を行う
    val todo = savedStateHandle.getLiveData<ToDo>("todo")
}