package com.example.todo.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task?
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}

//class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository {
//    override fun getAllTasks(): Flow<List<Task>> = dao.getAllTasks()
//    override suspend fun getTaskById(id: Long): Task? = dao.getTaskById(id)
//    override suspend fun insertTask(task: Task): Long = dao.insert(task)
//    override suspend fun updateTask(task: Task) = dao.update(task)
//    override suspend fun deleteTask(task: Task) = dao.delete(task)
//}
