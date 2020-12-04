package com.jhughes.getstuffdone.common

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import com.jhughes.getstuffdone.common.utils.AmbientBackDispatcher
import com.jhughes.getstuffdone.common.theme.GetStuffDoneTheme
import dev.chrisbanes.accompanist.insets.ExperimentalAnimatedInsets
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalAnimatedInsets::class)
@Composable
fun GetStuffDoneApp(
    backDispatcher: OnBackPressedDispatcher,
    content: @Composable () -> Unit
) {
    GetStuffDoneTheme {
        Providers(AmbientBackDispatcher provides backDispatcher) {
            ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                content()
            }
        }
    }
}