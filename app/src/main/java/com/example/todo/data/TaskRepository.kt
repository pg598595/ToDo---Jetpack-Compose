package com.example.todo.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task?
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}

