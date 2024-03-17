package com.example.todoapplication.page.deteail

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.application.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoDetailFragment:Fragment(R.layout.todo_detail_fragment) {
    private val vm: ToDoDetailViewModel by viewModels()
}