package com.example.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Task
import com.example.todo.data.TaskRepository
import com.example.todo.data.TaskRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repo: TaskRepository) : ViewModel() {
    // list of tasks
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    // single task loaded for editing
    private val _currentTask = MutableStateFlow<Task?>(null)
    val currentTask: StateFlow<Task?> = _currentTask.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getAllTasks().collect { _tasks.value = it }
        }
    }

    fun loadTask(id: Long) {
        viewModelScope.launch {
            _currentTask.value = repo.getTaskById(id)
        }
    }

    fun addOrUpdateTask(id: Long, title: String, description: String?, completed: Boolean) {
        viewModelScope.launch {
            val task = if (id == -1L) {
                Task(title = title, description = description, isCompleted = completed)
            } else {
                // when editing, preserve createdAt or set as desired
                val existing = repo.getTaskById(id)
                val created = existing?.createdAt ?: System.currentTimeMillis()
                Task(id = id, title = title, description = description, isCompleted = completed, createdAt = created)
            }
            repo.insertTask(task) // insert with REPLACE will work for create/update
        }
    }

    fun toggleCompleted(task: Task) {
        viewModelScope.launch {
            repo.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch { repo.deleteTask(task) }
    }
}

