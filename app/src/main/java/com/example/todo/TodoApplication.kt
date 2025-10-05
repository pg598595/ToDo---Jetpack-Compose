package com.example.todo

import android.app.Application
import com.example.todo.data.AppDatabase
import com.example.todo.data.TaskRepositoryImpl
import kotlin.getValue

class TodoApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskRepositoryImpl(database.taskDao()) }
}
