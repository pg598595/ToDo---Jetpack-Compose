package com.example.todo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo.ui.components.TaskItem
import com.example.todo.ui.theme.DarkRed
import com.example.todo.ui.theme.LightCream
import com.example.todo.ui.theme.SoftRed
import com.example.todo.ui.theme.WineRed
import com.example.todo.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("To-Do") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkRed,
                    titleContentColor = LightCream
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_edit/-1") },
                containerColor = WineRed,
                contentColor = LightCream
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add task")
            }
        }
    ) { padding ->
        if (tasks.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No tasks yet — add one!")
            }
        } else {
            LazyColumn(
                contentPadding =
                    PaddingValues(
                        top = padding.calculateTopPadding() + 16.dp, // extra 16.dp above list
                        bottom = padding.calculateBottomPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(tasks) { index, task ->
                    TaskItem(
                        task = task,
                        onCheckedChange = { viewModel.toggleCompleted(task) },
                        onClick = { navController.navigate("add_edit/${task.id}") },
                        onDelete = { viewModel.deleteTask(task) },
                        backgroundColor = if (index % 2 == 0) SoftRed else WineRed,
                        contentPadding = PaddingValues(16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

}
