package com.example.todoapplication.page.deteail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.model.todo.ToDo
import com.example.todoapplication.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoDetailViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // 中断、再開時に状態の保存と復元を行う
    val todo = savedStateHandle.getLiveData<ToDo>("todo")
    val errorMessage = MutableLiveData<String>()
    val deleted = MutableLiveData<Boolean>()

    fun delete() {
        viewModelScope.launch {
            try {
                val todo = this@ToDoDetailViewModel.todo.value ?: return@launch
                toDoRepository.delete(todo)
                // 削除後の完了処理
                deleted.value = true
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}