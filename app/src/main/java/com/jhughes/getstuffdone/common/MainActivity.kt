package com.jhughes.getstuffdone.common

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jhughes.getstuffdone.dashboard.DashboardViewModel
import com.jhughes.getstuffdone.dashboard.ui.DashboardScreen
import com.jhughes.getstuffdone.groupdetails.GroupDetailsViewModel
import com.jhughes.getstuffdone.groupdetails.ui.GroupDetailsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetStuffDoneApp(backDispatcher = onBackPressedDispatcher) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Dashboard.route()
                ) {
                    composable(Screen.Dashboard.route()) {
                        DashboardScreen(viewModel, navController)
                    }
                    composable(
                        route = Screen.GroupDetails.route(),
                        arguments = Screen.GroupDetails.arguments()
                    ) { backStackEntry ->
                        viewModel.selectedGroupId.value =
                            backStackEntry.arguments?.getInt(Screen.GroupDetails.ARGUMENT_GROUP_ID)
                        GroupDetailsScreen(viewModel, navController)
                    }
                }
            }
        }
    }
}