package com.example.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.TaskRepository
import com.example.todo.data.TaskRepositoryImpl

class TaskViewModelFactory(private val repo: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
