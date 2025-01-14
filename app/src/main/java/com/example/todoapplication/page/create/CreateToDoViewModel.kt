package com.example.todoapplication.page.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateToDoViewModel @Inject constructor(
    private val repo: ToDoRepository
): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val done = MutableLiveData<Boolean>()

    fun save(title: String, detail: String) {
        // titleが空だったら、エラメッセージを出す
        if (title.trim().isEmpty()) {
            errorMessage.value = "Please imput title"
            return
        }
        //　リポジトリ経由で実際の保存処理を行う
        viewModelScope.launch {
            try {
                repo.create(title, detail)
                done.value = true
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}