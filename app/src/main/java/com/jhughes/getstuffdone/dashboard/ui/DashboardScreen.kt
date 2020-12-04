package com.jhughes.getstuffdone.dashboard.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.jhughes.getstuffdone.common.Screen
import com.jhughes.getstuffdone.dashboard.DashboardViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import dev.chrisbanes.accompanist.insets.systemBarsPadding

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    navController: NavController
) {

    val groups = viewModel.getGroups().collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(text = "GetStuffDone") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.GroupDetails.routeWith())
            }) {
                Icon(imageVector = Icons.Outlined.Add)
            }
        },
    ) {
        GroupsList(
            modifier = Modifier.padding(it),
            gsdGroups = groups.value,
            onOpenGroupDetails = { group ->
                navController.navigate(
                    Screen.GroupDetails.routeWith(group)
                )
            })
    }
}
