package com.jhughes.getstuffdone.groupdetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.jhughes.getstuffdone.domain.model.GsdTask

@Composable
fun RowScope.GsdTaskRow(
    gsdTask: GsdTask,
    onUpdateTask: (GsdTask) -> Unit
) {

    var checkboxValueState by mutableStateOf(gsdTask.isCompleted)

    Checkbox(
        enabled = true,
        checked = checkboxValueState,
        onCheckedChange = {
            checkboxValueState = it
            onUpdateTask(gsdTask.copy(isCompleted = it))
        }
    )

    Text(
        modifier = Modifier.padding(start = 12.dp).weight(1f),
        text = gsdTask.description,
        textDecoration = if (checkboxValueState) {
            TextDecoration.LineThrough
        } else {
            null
        }
    )
}