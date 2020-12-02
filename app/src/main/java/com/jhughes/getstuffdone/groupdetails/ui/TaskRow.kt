package com.jhughes.getstuffdone.groupdetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhughes.getstuffdone.common.utils.backHandler
import com.jhughes.getstuffdone.domain.model.GsdTask


data class EditingUiState(
    val isChecked: Boolean = false,
    val text: String = ""
) {
    companion object {
        fun create(gsdTask: GsdTask?): EditingUiState {
            return EditingUiState(
                isChecked = gsdTask?.isCompleted ?: false,
                text = gsdTask?.description ?: ""
            )
        }
    }
}

fun EditingUiState.createNewTaskFromState(): GsdTask {
    return GsdTask(id = 0, isCompleted = isChecked, description = text)
}

sealed class TaskRowUiState {
    data class Inactive(val gsdTask: GsdTask) : TaskRowUiState()
    data class Editing(val editingUiState: EditingUiState = EditingUiState()) : TaskRowUiState()
    object Prompt : TaskRowUiState()
}

@OptIn(ExperimentalFocus::class)
@Composable
fun TaskRow(
    task: GsdTask? = null,
    onSave: (GsdTask) -> Unit = {},
    onDelete: (GsdTask) -> Unit = {}
) {
    var uiState = remember {
        mutableStateOf(
            if (task != null) {
                TaskRowUiState.Inactive(task)
            } else {
                TaskRowUiState.Prompt
            }
        )
    }

    backHandler(
        enabled = uiState.value is TaskRowUiState.Editing,
        onBack = {
            uiState.value = if (task != null) {
                TaskRowUiState.Inactive(task)
            } else {
                TaskRowUiState.Prompt
            }
        }
    )

    if (uiState.value is TaskRowUiState.Prompt) {
        AddTaskPrompt(
            uiState = uiState.value,
            onAddTask = {
                uiState.value = TaskRowUiState.Editing()
            })
    } else {
        EditableTaskRow(
            uiState = uiState.value,
            isChecked = when (val state = uiState.value) {
                is TaskRowUiState.Editing -> state.editingUiState.isChecked
                is TaskRowUiState.Inactive -> state.gsdTask.isCompleted
                else -> false
            },
            text = when (val state = uiState.value) {
                is TaskRowUiState.Editing -> state.editingUiState.text
                is TaskRowUiState.Inactive -> state.gsdTask.description
                else -> ""
            },
            onCheckedChange = { checked ->
                when (val state = uiState.value) {
                    is TaskRowUiState.Editing -> {
                        if (task != null) {
                            onSave(
                                task.copy(
                                    isCompleted = checked,
                                    description = state.editingUiState.text
                                )
                            )
                        } else {
                            onSave(state.editingUiState.createNewTaskFromState())
                        }
                    }
                    is TaskRowUiState.Inactive -> {
                        val updatedTask = state.gsdTask.copy(isCompleted = checked)
                        uiState.value = TaskRowUiState.Inactive(updatedTask)
                        onSave(updatedTask)
                    }
                    else -> {
                        //no-op
                    }
                }
            },
            onTextFieldFocused = { focusState ->
                uiState.value = when {
                    focusState == FocusState.Active -> {
                        TaskRowUiState.Editing(EditingUiState.create(task))
                    }
                    task != null -> {
                        TaskRowUiState.Inactive(task)
                    }
                    else -> {
                        TaskRowUiState.Prompt
                    }
                }
            },
            onDelete = {
                if (task != null) {
                    onDelete(task)
                } else {
                    uiState.value = TaskRowUiState.Prompt
                }
            },
            onDone = { textFieldValue, isChecked ->
                val taskToSave = task?.copy(
                    isCompleted = isChecked,
                    description = textFieldValue.text
                ) ?: GsdTask(0, description = textFieldValue.text, isCompleted = isChecked)
                onSave(taskToSave)
                if (task == null) {
                    //signals we were adding a new one so return this composable to prompt state
                    uiState.value = TaskRowUiState.Prompt
                } else {
                    uiState.value = TaskRowUiState.Inactive(taskToSave)
                }
            }
        )
    }
}

@OptIn(ExperimentalFocus::class)
@Composable
fun EditableTaskRow(
    uiState: TaskRowUiState,
    isChecked: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit,
    onTextFieldFocused: (FocusState) -> Unit,
    onDone: (TextFieldValue, Boolean) -> Unit,
    onDelete: () -> Unit
) {

    // Grab a reference to the keyboard controller whenever text input starts
    var keyboardController by remember { mutableStateOf<SoftwareKeyboardController?>(null) }

    // Show or hide the keyboard
    onCommit(keyboardController, uiState) { // Guard side-effects against failed commits
        keyboardController?.let {
            if (uiState is TaskRowUiState.Editing) {
                it.showSoftwareKeyboard()
            } else {
                it.hideSoftwareKeyboard()
            }
        }
    }

    var textValueState by remember { mutableStateOf(TextFieldValue(text)) }
    var checkboxValueState by remember { mutableStateOf(isChecked) }

    val focusRequester = FocusRequester()

    Row(
        modifier = Modifier.preferredHeight(54.dp).padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Checkbox(
            enabled = true,
            checked = checkboxValueState,
            onCheckedChange = {
                checkboxValueState = it
                onCheckedChange(it)
            }
        )

        var lastFocusState by remember { mutableStateOf(FocusState.Inactive) }

        BasicTextField(
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(start = 12.dp).weight(1f)
                .focusRequester(focusRequester)
                .focusObserver { state ->
                    if (lastFocusState != state) {
                        onTextFieldFocused(state)
                    }
                    lastFocusState = state
                },
            value = textValueState,
            onValueChange = {
                textValueState = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onImeActionPerformed = { imeAction ->
                if (imeAction == ImeAction.Done) {
                    onDone(textValueState, checkboxValueState)
                }
            }
        )

        if (uiState is TaskRowUiState.Editing) {
            IconButton(onClick = { onDelete() }) {
                Icon(asset = Icons.Default.Close)
            }
        }
    }

    onCommit {
        if (uiState is TaskRowUiState.Editing) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
fun AddTaskPrompt(uiState: TaskRowUiState, onAddTask: () -> Unit) {
    Row(
        modifier = Modifier.preferredHeight(54.dp).padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "+ add task", modifier = Modifier.clickable(onClick = onAddTask))
    }
}
