package com.jhughes.getstuffdone.groupdetails.ui

import android.util.Log
import androidx.compose.animation.animate
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.ui.tooling.preview.Preview
import com.jhughes.getstuffdone.dashboard.DashboardViewModel
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.common.theme.GetStuffDoneTheme
import com.jhughes.getstuffdone.common.theme.progressIndicatorBackground
import com.jhughes.getstuffdone.domain.model.GsdTask
import dev.chrisbanes.accompanist.insets.*

@OptIn(ExperimentalFocus::class)
@Composable
fun GroupDetailsScreen(
    viewModel: DashboardViewModel,
    navController: NavController
) {
    val selectedGroup: GsdGroup? by viewModel.getSelectedGroup().collectAsState(null)

    var showDeleteConfirmationDialog by savedInstanceState { false }
    if (showDeleteConfirmationDialog) {
        DeleteConfirmationDialog(
            onConfirm = {
                selectedGroup?.let {
                    viewModel.deleteGroup(it)
                }
                navController.popBackStack()
            },
            onDismiss = { showDeleteConfirmationDialog = false })
    }

    Surface {
        Column(modifier = Modifier.fillMaxSize().navigationBarsWithImePadding()) {

            GroupDetailsTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                elevation = 8.dp,
                tasksCompletedCount = selectedGroup?.tasks?.filter { it.isCompleted }?.size,
                totalTasksCount = selectedGroup?.tasks?.size,
                onBackPressed = {
                    navController.popBackStack()
                },
                onDeleteGroup = {
                    showDeleteConfirmationDialog = true
                }
            )

            var onSaveAndContinue by remember { mutableStateOf(false) }

            ScrollableColumn {
                GroupTitleTextField(
                    title = mutableStateOf(selectedGroup?.title ?: ""),
                    onSaveTitle = { newTitle ->
                        if (selectedGroup != null) {
                            viewModel.saveGroup(selectedGroup!!.copy(title = newTitle))
                        } else {
                            viewModel.createGroup(GsdGroup(title = newTitle))
                        }
                    })

                (selectedGroup?.tasks ?: emptyList()).forEachIndexed { index, task ->
                    TaskRow(
                        task = task,
                        onSave = { updatedTask ->
                            onSaveAndContinue = false
                            viewModel.saveTask(updatedTask)
                        },
                        onDelete = { taskToDelete ->
                            onSaveAndContinue = false
                            viewModel.deleteTask(taskToDelete)
                        }
                    )
                }
                if (selectedGroup != null) {
                    AddTaskRow(
                        startAsEditing = onSaveAndContinue,
                        onSaveNewTask = { checked, text ->
                            onSaveAndContinue = true
                            viewModel.saveTask(GsdTask(0, text, checked))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = { Text(text = "Are you sure you want to delete this list?") },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text(text = "DELETE")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "CLOSE")
            }
        }
    )
}