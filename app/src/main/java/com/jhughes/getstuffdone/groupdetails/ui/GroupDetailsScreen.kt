package com.jhughes.getstuffdone.groupdetails.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jhughes.getstuffdone.dashboard.DashboardViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalFocus::class)
@Composable
fun GroupDetailsScreen(
    viewModel: DashboardViewModel,
    navController: NavController
) {

    val selectedGroup = viewModel.getSelectedGroup().collectAsState(null)

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(text = "Details demo") },
                modifier = Modifier.statusBarsPadding()
            )
            ScrollableColumn(Modifier.weight(1f)) {
                BasicTextField(
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(fontSize = 14.sp),
                    value = selectedGroup.value?.title ?: "",
                    onValueChange = {})
                Divider()
                (selectedGroup.value?.tasks ?: emptyList()).forEach { task ->
                    TaskRow(
                        task = task,
                        onSave = { viewModel.saveTask(it) },
                        onDelete = { viewModel.deleteTask(it) }
                    )
                }
                TaskRow(onSave = {
                    viewModel.saveTask(it)
                })
            }
        }
    }
}