package com.jhughes.getstuffdone.groupdetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jhughes.getstuffdone.common.utils.backHandler
import com.jhughes.getstuffdone.domain.model.GsdTask
import dev.chrisbanes.accompanist.insets.AmbientWindowInsets

@Composable
fun TaskRow(
    modifier: Modifier = Modifier,
    task: GsdTask,
    onSave: (GsdTask) -> Unit = {},
    onDelete: (GsdTask) -> Unit = {},
) {
    var inEditState by remember { mutableStateOf(false) }

    backHandler(
        enabled = inEditState,
        onBack = {
            inEditState = false
        }
    )

    Row(
        modifier = modifier
            .preferredHeight(54.dp)
            .clickable(
                indication = null,
                onClick = {
                    if (!inEditState) {
                        inEditState = true
                    }
                })
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (inEditState) {
            EditableTaskContent(
                isChecked = task.isCompleted,
                text = task.description,
                onCheckedChange = { checked ->
                    onSave(task.copy(isCompleted = checked))
                },
                onDelete = {
                    inEditState = false
                    onDelete(task)
                },
                onDone = { textFieldValue, isChecked ->
                    inEditState = false
                    val taskToSave = task.copy(
                        isCompleted = isChecked,
                        description = textFieldValue.text
                    )
                    onSave(taskToSave)
                },
                onExitEditableState = {
                    inEditState = false
                }
            )
        } else {
            GsdTaskRow(
                gsdTask = task,
                onUpdateTask = onSave
            )
        }
    }
}

@Composable
fun AddTaskRow(
    modifier: Modifier = Modifier,
    startAsEditing: Boolean = false,
    onSaveNewTask: (checked: Boolean, text: String) -> Unit = { _, _ -> }
) {
    var inEditState by remember { mutableStateOf(startAsEditing) }

    backHandler(
        enabled = inEditState,
        onBack = {
            inEditState = false
        }
    )

    Row(
        modifier = modifier.preferredHeight(54.dp)
            .clickable(
                indication = null,
                onClick = {
                    if (!inEditState) {
                        inEditState = true
                    }
                })
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (inEditState) {
            EditableTaskContent(
                text = "",
                isChecked = false,
                onDelete = { inEditState = false },
                onDone = { textFieldValue, isChecked ->
                    onSaveNewTask(isChecked, textFieldValue.text)
                },
                onExitEditableState = { inEditState = false }
            )
        } else {
            Text(modifier = Modifier.padding(start = 12.dp).weight(1f), text = "+ add task")
        }
    }
}
