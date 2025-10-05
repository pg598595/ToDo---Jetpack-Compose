package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.ui.AddEditTaskScreen
import com.example.todo.ui.TaskListScreen
import com.example.todo.ui.theme.ToDoTheme
import com.example.todo.viewmodel.TaskViewModel
import com.example.todo.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = (application as TodoApplication).repository
        val viewModelFactory = TaskViewModelFactory(repo)

        setContent {
            ToDoTheme { // your Material3 theme wrapper
                val navController = rememberNavController()
                val vm: TaskViewModel = viewModel(factory = viewModelFactory)
                NavHost(navController = navController, startDestination = "task_list") {
                    composable("task_list") {
                        TaskListScreen(navController = navController, viewModel = vm)
                    }
                    composable(
                        "add_edit/{taskId}",
                        arguments = listOf(
                            navArgument("taskId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ) { backStack ->
                        val taskId = backStack.arguments?.getLong("taskId") ?: -1L
                        AddEditTaskScreen(navController = navController, viewModel = vm, taskId = taskId)
                    }
                }
            }
        }
    }
}
