package com.jhughes.getstuffdone.dashboard.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.jhughes.getstuffdone.common.Screen
import com.jhughes.getstuffdone.dashboard.DashboardViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    navController: NavController
) {

    val groups = viewModel.getGroups().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "GetStuffDone") },
                modifier = Modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.testCreateNewGroup()
            }) {
                Icon(imageVector = Icons.Outlined.Add)
            }
        },
    ) {
        GroupsList(gsdGroups = groups.value, onClick = { group ->
            navController.navigate(Screen.GroupDetails.route(group))
        })
    }
}
