package com.example.todoapplication

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.application.databinding.TodoItemBinding
import com.example.todoapplication.model.todo.ToDo

class ToDoAdapter: ListAdapter<ToDo, ToDoAdapter.ViewHolder>(callbacks) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bindTo(todo)
    }

    class ViewHolder(
        private val binding: TodoItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindTo(todo: ToDo) {
            binding.titleText.text = todo.title
            binding.createdText.text = DateFormat.format("yyyy-MM-dd hh:mm:ss", todo.created
        }
    }

    companion object {
        private  val callbacks = object: DiffUtil.ItemCallback<ToDo>() {
            override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                // 同じアイテムかどうかを判別する
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                // リストに表示する内容が同じかどうかを判別する
                return oldItem.title == newItem.title &&
                        oldItem.created == newItem.created
            }

        }
    }
}
