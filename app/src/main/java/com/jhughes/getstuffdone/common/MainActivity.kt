package com.jhughes.getstuffdone.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhughes.getstuffdone.dashboard.DashboardViewModel
import com.jhughes.getstuffdone.dashboard.ui.DashboardScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetStuffDoneApp(backDispatcher = onBackPressedDispatcher) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "dashboard"
                ) {
                    composable("dashboard") {
                        val viewModel: DashboardViewModel =
                            viewModel(factory = defaultViewModelProviderFactory)
                        DashboardScreen(viewModel, navController)
                    }
                }
            }
        }
    }
}