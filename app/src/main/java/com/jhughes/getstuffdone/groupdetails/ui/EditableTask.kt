package com.jhughes.getstuffdone.groupdetails.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focusObserver
import androidx.compose.ui.focusRequester
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFocus::class)
@Composable
fun RowScope.EditableTaskContent(
    isChecked: Boolean = false,
    text: String = "",
    onCheckedChange: (Boolean) -> Unit = {},
    onDone: (TextFieldValue, Boolean) -> Unit = {_, _ ->},
    onDelete: () -> Unit = {},
    onExitEditableState: () -> Unit = {}
) {
    var textValueState by remember { mutableStateOf(TextFieldValue(text)) }
    var checkboxValueState by remember { mutableStateOf(isChecked) }

    val focusRequester = FocusRequester()

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
                if (lastFocusState != state && state == FocusState.Inactive) {
                    onExitEditableState()
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
                textValueState = TextFieldValue("")
                checkboxValueState = false
            }
        }
    )

    IconButton(onClick = { onDelete() }) {
        Icon(imageVector = Icons.Default.Close)
    }

    onCommit {
        focusRequester.requestFocus()
    }
}