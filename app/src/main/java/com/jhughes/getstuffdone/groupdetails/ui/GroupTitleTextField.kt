package com.jhughes.getstuffdone.groupdetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GroupTitleTextField(
    title : MutableState<String> = mutableStateOf(""),
    onSaveTitle: (String) -> Unit
) {
    // Grab a reference to the keyboard controller whenever text input starts
    var keyboardController by remember { mutableStateOf<SoftwareKeyboardController?>(null) }

    BasicTextField(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        textStyle = TextStyle(fontSize = 20.sp),
        value = title.value,
        onValueChange = {
            title.value = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        onImeActionPerformed = { imeAction ->
            if (imeAction == ImeAction.Done) {
                keyboardController?.hideSoftwareKeyboard()
                onSaveTitle(title.value)
            }
        },
        onTextInputStarted = {
            keyboardController = it
        }
    )
    Divider(modifier = Modifier.padding(bottom = 8.dp))
}